/*
 * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *
 *                              Licensed under the Apache License, Version 2.0 (the "License");
 *                              you may not use this file except in compliance with the License.
 *                              You may obtain a copy of the License at
 *
 *                                  http://www.apache.org/licenses/LICENSE-2.0
 *
 *                              Unless required by applicable law or agreed to in writing, software
 *                              distributed under the License is distributed on an "AS IS" BASIS,
 *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *                              See the License for the specific language governing permissions and
 *                              limitations under the License.
 */

package client

import akka.actor.{Actor, ActorSystem}
import client.generalpractitioner.GeneralPractitionerActor
import client.patient.PatientActor
import client.surgeon.SurgeonActor
import domainmodel.{DoctorID, PatientID}

import java.util.UUID

object DomainFigureFactory {

  /**
   * Factory method for actors.
   *
   * @param token       , token for authentication.
   * @param role        , role of actor.
   * @param actorSystem , actor system.
   * @return required actor.
   */
  def createDomainFigure(token: String, role: String)(implicit
    actorSystem: ActorSystem
  ): (Actor, String) =
    role match {
      case "patient" =>
        val patientID = PatientID(UUID.randomUUID().toString)
        Tuple2(new PatientActor(patientID, token), s"patient$patientID")
      case "surgeon" =>
        val doctorID = DoctorID(UUID.randomUUID().toString)
        Tuple2(new SurgeonActor(doctorID, token), s"surgeon$doctorID")
      case "general practitioner" =>
        val doctorID = DoctorID(UUID.randomUUID().toString)
        Tuple2(
          new GeneralPractitionerActor(doctorID, token),
          s"generalpractitioner$doctorID"
        )
    }

}
