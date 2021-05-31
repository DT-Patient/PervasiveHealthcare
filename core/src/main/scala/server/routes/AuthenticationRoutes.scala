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

package server.routes

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{
  as,
  authorize,
  complete,
  entity,
  headerValueByName,
  onSuccess,
  pathEnd,
  pathPrefix,
  post,
  _
}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import domainmodel.User
import json.RequestJsonFormats.acceptedJsonFormat
import json.UserJsonFormat.userJsonFormat
import server.models.JwtAuthentication.isLogged
import server.models.Protocol
import server.models.Protocol._
import server.utils.Utils

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
 * This class contains the implementation of all the authentication routes.
 *
 * @param authenticationController authentication controller
 * @param system                   represent the actor system
 */
class AuthenticationRoutes(
  authenticationController: ActorRef[Protocol.CQRSAction]
)(implicit val system: ActorSystem[_]) {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  /**
   * Method for login
   *
   * @param user user who needs to login
   * @return confirmation
   */
  def login(user: User): Future[Confirmation] = {
    authenticationController.ask(Login(user, _))
  }

  /**
   * Method for logout
   *
   * @param tokenId user's token
   * @return confirmation
   */
  def logout(tokenId: String): Future[Confirmation] = {
    authenticationController.ask(Logout(tokenId, _))

  }

  val authenticationRoutes: Route = {
    pathPrefix("api") {
      pathPrefix("login") {
        pathEnd {
          post {
            entity(as[User]) { user =>
              val hashedPassword =
                Utils.getHashedPassword(user.password) //password hashing
              val newUser = User(user.id, hashedPassword)
              onSuccess(login(newUser)) { response =>
                response match {
                  case _: LoginAccepted => complete(StatusCodes.OK, response)
                  case _: Rejected      => complete(StatusCodes.BadRequest, response)
                  case _                => complete(StatusCodes.BadRequest, response)
                }
              }
            }
          }
        }
      } ~
        pathPrefix("logout") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(isLogged(value)) {
                  onSuccess(logout(value)) { response =>
                    response match {
                      case _: Accepted => complete(StatusCodes.OK, response)
                      case _: Rejected =>
                        complete(StatusCodes.BadRequest, response)
                      case _ => complete(StatusCodes.BadRequest, response)
                    }
                  }
                }
              }
            }
          }
        }
    }
  }

}
