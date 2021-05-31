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
import server.models.Protocol._

object EnumerationController {

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
      case GetRoles(replyTo) =>
        val res = ReadModel.getRole
        replyTo ! res
        Behaviors.same
      case GetGenders(replyTo) =>
        val res = ReadModel.getGender
        replyTo ! res
        Behaviors.same
      case GetAllergies(replyTo) =>
        val res = ReadModel.getAllergy
        replyTo ! res
        Behaviors.same
      case GetBloodTypes(replyTo) =>
        val res = ReadModel.getBloodType
        replyTo ! res
        Behaviors.same
      case GetRh(replyTo) =>
        val res = ReadModel.getRh
        replyTo ! res
        Behaviors.same
      case GetSpecializations(replyTo) =>
        val res = ReadModel.getSpecialization
        replyTo ! res
        Behaviors.same
      case GetKinshipDegrees(replyTo) =>
        val res = ReadModel.getKinshipDegree
        replyTo ! res
        Behaviors.same
      case GetChestPainTypes(replyTo) =>
        val res = ReadModel.getChestPainType
        replyTo ! res
        Behaviors.same
      case GetRestingECG(replyTo) =>
        val res = ReadModel.getRestingECG
        replyTo ! res
        Behaviors.same
      case GetSlopeST(replyTo) =>
        val res = ReadModel.getSlopeST
        replyTo ! res
        Behaviors.same
      case GetThals(replyTo) =>
        val res = ReadModel.getThal
        replyTo ! res
        Behaviors.same
      case _ => throw new IllegalArgumentException()
    }
}
