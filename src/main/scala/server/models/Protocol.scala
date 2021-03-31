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
package server.models

import akka.actor.typed.ActorRef
import domainmodel.User
import domainmodel.professionalfigure.{Anesthetist, DoctorID, GeneralPractitioner, Instrumentalist, Rescuer, Surgeon, WardNurse}

object Protocol {

  sealed trait Command

  sealed trait Event

  sealed trait Confirmation

  final case class LoginAccepted(description: String, token: String) extends Confirmation

  final case class Accepted(description: String) extends Confirmation

  final case class Rejected(reason: String) extends Confirmation

  //Authentication protocol
  final case class Login(user: User, replyTo: ActorRef[Confirmation]) extends Command

  //Administrator protocol is also authentication for the signup
  final case class InsertSurgeon(surgeon: Surgeon, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateSurgeon(id: String, surgeon: Surgeon, replyTo: ActorRef[Confirmation]) extends Command

  final case class RemoveSurgeon(surgeon: Surgeon, replyTo: ActorRef[Confirmation]) extends Command

  final case class SurgeonAdded(surgeon: Surgeon) extends Event

  final case class SurgeonUpdated(id: DoctorID, surgeon: Surgeon) extends Event

  final case class SurgeonRemoved(surgeon: Surgeon) extends Event

  final case class InsertAnesthetist(anesthetist: Anesthetist, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateAnesthetist(id: String, anesthetist: Anesthetist, replyTo: ActorRef[Confirmation]) extends Command

  final case class InsertGeneralPractitioner(generalPractitioner: GeneralPractitioner, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateGeneralPractitioner(id: String, generalPractitioner: GeneralPractitioner, replyTo: ActorRef[Confirmation]) extends Command

  final case class InsertInstrumentalist(instrumentalist: Instrumentalist, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateInstrumentalist(id: String, instrumentalist: Instrumentalist, replyTo: ActorRef[Confirmation]) extends Command

  final case class InsertRescuer(rescuer: Rescuer, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateRescuer(id: String, rescuer: Rescuer, replyTo: ActorRef[Confirmation]) extends Command

  final case class InsertWardNurse(wardNurse: WardNurse, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdateWardNurse(id: String, wardNurse: WardNurse, replyTo: ActorRef[Confirmation]) extends Command

  /*final case class InsertPatient(patient: Patient, replyTo: ActorRef[Confirmation]) extends Command

  final case class UpdatePatient(id: String, patient: Patient, replyTo: ActorRef[Confirmation]) extends Command*/

}
