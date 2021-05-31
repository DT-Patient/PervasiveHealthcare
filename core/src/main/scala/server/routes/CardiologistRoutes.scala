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
import akka.http.scaladsl.server.Directives.{complete, pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import domainmodel.{CardiologyVisit, DoctorID}
import json.CardiologyVisitJsonFormat.cardiologyVisitJsonFormat
import json.RequestJsonFormats.{acceptedJsonFormat, immSetFormat}
import server.models.JwtAuthentication.hasCardiologistPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
 * This class contains the implementation of all the routes that the cardiologist can call up to insert or update elements in the db.
 *
 * @param cardiologistController cardiologist controller
 * @param system                 represent the actor system
 */
class CardiologistRoutes(cardiologistController: ActorRef[Protocol.CQRSAction])(
  implicit val system: ActorSystem[_]
) {
  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  /**
   * Method to insert cardiology visit in the db
   *
   * @param cardiologyVisit cardiology visit to insert
   * @return confirmation
   */
  def insertCardiologyVisit(
    cardiologyVisit: CardiologyVisit
  ): Future[Confirmation] =
    cardiologistController.ask(InsertCardiologyVisit(cardiologyVisit, _))

  /**
   * Method to get all the cardiology visit for the specific cardiologist.
   *
   * @param doctorID the cardiologist ID
   * @return a set of cardiology visits
   */
  def getCardiologyVisits(doctorID: DoctorID): Future[Set[CardiologyVisit]] =
    cardiologistController.ask(GetCardiologyVisits(doctorID, _))

  val cardiologistRoutes: Route =
    pathPrefix("api") {
      pathPrefix("cardiologyvisits") {
        pathEnd {
          post {
            headerValueByName("x-access-token") { value =>
              authorize(hasCardiologistPermissions(value)) {
                entity(as[CardiologyVisit]) { cardiologyVisit =>
                  onSuccess(insertCardiologyVisit(cardiologyVisit)) {
                    response =>
                      response match {
                        case _: Accepted =>
                          complete(StatusCodes.Created, response)
                        case _: Rejected =>
                          complete(StatusCodes.BadRequest, response)
                        case _ => complete(StatusCodes.BadRequest, response)
                      }
                  }
                }
              }
            }
          }
        } ~
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCardiologistPermissions(value)) {
                  onSuccess(getCardiologyVisits(DoctorID(id))) { response =>
                    complete(StatusCodes.OK, response)
                  }
                }
              }
            }
          }
      }
    }
}
