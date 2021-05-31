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
import server.models.JwtAuthentication
import server.models.JwtAuthentication.Token
import server.models.Protocol._

import scala.util.Random

/**
 * This object represents the set of actions that are carried out following a authentication's request.
 */
object AuthenticationController {

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
      case Login(user, replyTo) =>
        val res = Repository.auth.login(user)
        if (res._1.nonEmpty) {
          val token: String = Random.alphanumeric take 64 mkString ""
          JwtAuthentication.tokens =
            JwtAuthentication.tokens.addNewToken(Token((token, res._1.get.id)))
          replyTo ! LoginAccepted(res._1.get, res._2, token)
        } else {
          replyTo ! Rejected(res._2)
        }
        Behaviors.same
      case Logout(tokenId, replyTo) =>
        if (JwtAuthentication.tokens.tokens.contains(tokenId)) {
          JwtAuthentication.tokens =
            JwtAuthentication.tokens.removeToken(tokenId)
          replyTo ! Accepted("Logout avvenuto.")
        } else {
          replyTo ! Accepted(
            "Non puoi effettuare il logout senza aver fatto il login."
          )
        }
        Behaviors.same
      case _ => throw new IllegalArgumentException()
    }
}
