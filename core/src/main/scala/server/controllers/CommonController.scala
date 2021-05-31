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
import cqrs.readmodel.RMUtility
import domainmodel.{DoctorID, PatientID}
import server.models.Protocol._

/**
 * Controller object for common CQRS actions.
 */
object CommonController {

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
      case GetSurgeon(id, replyTo) =>
        val res = RMUtility.recreateSurgeonState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetAnesthetist(id, replyTo) =>
        val res = RMUtility.recreateAnesthetistState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetInstrumentalist(id, replyTo) =>
        val res = RMUtility.recreateInstrumentalistState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetGeneralPractitioner(id, replyTo) =>
        val res = RMUtility.recreateGeneralPractitionerState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetRescuer(id, replyTo) =>
        val res = RMUtility.recreateRescuerState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetWardNurse(id, replyTo) =>
        val res = RMUtility.recreateWardNurseState(DoctorID(id))
        replyTo ! res
        Behaviors.same
      case GetPatient(id, replyTo) =>
        val res = RMUtility.recreatePatientState(PatientID(id))
        replyTo ! res
        Behaviors.same
    }
}
