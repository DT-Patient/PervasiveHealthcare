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
import cqrs.readmodel.ReadModel
import cqrs.writemodel.Repository
import server.models.Protocol._

/**
 * This object represents the set of actions that are carried out following a resource's request by anesthetist.
 */
object AnesthetistController {

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
      case InsertMedicalRecord(medicalRecord, replyTo) =>
        val res =
          Repository.anesthetistRepository.insertMedicalRecord(medicalRecord)
        if (res == "Medical record created.") {
          ReadModel.insertMedicalRecord(medicalRecord.patientID, medicalRecord)
          replyTo ! Accepted(res)
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
      case UpdateMedicalRecord(medicalRecordID, medicalRecord, replyTo) =>
        val res = Repository.anesthetistRepository.updateMedicalRecord(
          medicalRecordID,
          medicalRecord
        )
        if (res == "Medical record updated.") {
          ReadModel.updateMedicalRecord(medicalRecord.patientID, medicalRecord)
          replyTo ! Accepted(res)
        } else {
          replyTo ! Rejected(res)
        }
        Behaviors.same
    }
}
