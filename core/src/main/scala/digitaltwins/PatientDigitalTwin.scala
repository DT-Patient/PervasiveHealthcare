/*
 *
 *  * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *  *
 *  *                              Licensed under the Apache License, Version 2.0 (the "License");
 *  *                              you may not use this file except in compliance with the License.
 *  *                              You may obtain a copy of the License at
 *  *
 *  *                                  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *                              Unless required by applicable law or agreed to in writing, software
 *  *                              distributed under the License is distributed on an "AS IS" BASIS,
 *  *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *                              See the License for the specific language governing permissions and
 *  *                              limitations under the License.
 *
 */

package digitaltwins

import akka.actor
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import behaviours.CardiologyDiseasesPredictor
import cqrs.readmodel.{RMUtility, ReadModel}
import cqrs.writemodel.Repository.patientRepository
import domainmodel.Patient.Patient
import domainmodel.{
  CardiologyDiseasePrediction,
  CardiologyDiseasesPresence,
  CardiologyPrediction,
  CardiologyVisit,
  PatientID
}
import java.time.{LocalDate, Period}

/**
 * interface Message.
 */
trait Message

/**
 * Cardiology Visit Inserted Message.
 */
case class CardiologyVisitInserted() extends Message

object PatientDigitalTwin {

  val digitalTwins: actor.ActorSystem =
    akka.actor.ActorSystem.create("PervasiveHealthcare")

  var patientsDigitalTwins: Map[PatientID, ActorRef] =
    Map[PatientID, ActorRef]()

  /**
   * Set props.
   *
   * @param patientID , id of patient.
   * @return props.
   */
  def props(patientID: PatientID): Props = {
    Props(PatientDigitalTwin(patientID))
  }

  /**
   * Initialize.
   */
  def initialize(): Unit = {
    RMUtility.getAllPatientID.foreach(id =>
      patientsDigitalTwins =
        patientsDigitalTwins + (id -> digitalTwins.actorOf(props(id), id.value))
    )
  }

  case class PatientDigitalTwin(patientID: PatientID)
    extends Actor
    with ActorLogging {

    /**
     * Make cardiology prediction.
     *
     * @param patient , patient to analyse.
     * @return a cardiology disease prediction.
     */
    def makeCardiologyPrediction(
      patient: Patient
    ): CardiologyDiseasePrediction = {
      val cardiologyVisits = getLastVisit(patient)
      val age =
        Period.between(patient.birthDate, LocalDate.now()).toTotalMonths / 24
      CardiologyDiseasesPredictor.predict(
        age.toInt,
        patient.gender,
        cardiologyVisits
      )
    }

    /**
     * Method to get the last visit of a patient.
     *
     * @param patient , the patient.
     * @return the last cardiology visit inserted.
     */
    def getLastVisit(patient: Patient): CardiologyVisit = {
      patient.cardiologyVisitHistory.get.history.toList
        .sortWith((c1, c2) =>
          c1.visitDate.visitDate.isAfter(c2.visitDate.visitDate)
        )
        .head
    }

    private lazy val onInteractionBehaviour: Receive = {
      case CardiologyVisitInserted =>
        val patientOp = RMUtility.recreatePatientState(patientID)
        if (patientOp.nonEmpty) {
          val patient = patientOp.get
          val prediction = this.makeCardiologyPrediction(patient)
          if (
            prediction.isInstanceOf[
              CardiologyDiseasesPresence
            ] && patient.generalPractitionerInfo.nonEmpty
          ) {
            val prediction = CardiologyPrediction(
              patientID,
              patient.generalPractitionerInfo.get.doctorID,
              getLastVisit(patient)
            )
            patientRepository.insertCardiologyPrediction(prediction)
            ReadModel.insertCardiologyPrediction(
              patient.generalPractitionerInfo.get.doctorID,
              prediction
            )
          }
        }
    }

    override def receive: Receive = this.onInteractionBehaviour
  }
}
