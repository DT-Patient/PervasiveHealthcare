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

package server.controllers

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import cqrs.readmodel.{RMUtility, ReadModel}
import cqrs.writemodel.Repository
import digitaltwins.{CardiologyVisitInserted, PatientDigitalTwin}
import server.models.Protocol._

/**
 * This object represents the set of actions that are carried out following a resource's request by cardiologist.
 */
object CardiologistController {

  /**
   * Create a new handleAction.
   *
   * @return an instance of a Behavior[CQRSAction]
   */
  def apply(): Behavior[CQRSAction] = handleAction()

  /**
   * Behaviors for received messages.
   *
   * @return behaviour confirmation
   */
  def handleAction(): Behavior[CQRSAction] =
    Behaviors.receiveMessage {
      case InsertCardiologyVisit(cardiologyVisit, replyTo) =>
        val res: String =
          Repository.cardiologyRepository.insertCardiologyVisit(cardiologyVisit)
        if (res == "Cardiology report created.") {
          ReadModel.insertCardiologyVisit(
            cardiologyVisit.patientID,
            cardiologyVisit
          )
          PatientDigitalTwin.patientsDigitalTwins
            .filter(_._1 == cardiologyVisit.patientID)
            .head
            ._2 ! CardiologyVisitInserted

          replyTo ! Accepted(res)
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
      case GetCardiologyVisits(doctorID, replyTo) =>
        val res = RMUtility.getAllCardiologyVisitForDoctor(doctorID)
        replyTo ! res
        Behaviors.same
    }
}
