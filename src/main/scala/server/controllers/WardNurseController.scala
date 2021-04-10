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
import cqrs.writemodel.Repository
import domainmodel.medicalrecords.{DischargeLetter, MedicalRecord}
import domainmodel.medicalrecords.clinicaldiary.ClinicalDiary
import server.models.Protocol._

object WardNurseController {

  def apply(): Behavior[Command] = handleCommand()
  def handleCommand(): Behavior[Command] =
    Behaviors.receiveMessage {
      case InsertMedicalRecord(medicalRecord, replyTo) =>
        val res = Repository.wardNurseRepository.insertMedicalRecord(medicalRecord)
        if (res == "Medical record created.") { //if there is an error the events are not stored, otherwise the events will be stored.
          //ReadModel().createMedicalRecord(medicalRecord)
          replyTo ! Accepted(res)
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
      case UpdateMedicalRecord(medicalRecordID, medicalRecord, replyTo) =>
        val res = Repository.wardNurseRepository.updateMedicalRecord(medicalRecordID , medicalRecord)
        if (res == "Medical record updated.") {
          // ReadModel().updateMedicalRecord(medicalRecord)
          replyTo ! Accepted(res)// actions that are to be performed after successful.
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
      case UpdateGeneralInfo(patientID, generalInfo, replyTo) =>
        val res = Repository.wardNurseRepository.updateGeneralInfo(patientID , generalInfo)
        if (res == "General info updated.") {
          // ReadModel().updateGeneralInfo(generalInfo)
          replyTo ! Accepted(res)// actions that are to be performed after successful.
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
    }
}
