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
import domainmodel.Patient.Patient
import domainmodel.professionalfigure._
import json.PatientJsonFormat.PatientJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat._
import server.models.JwtAuthentication.hasCommonPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class CommonRoutes(commonController: ActorRef[Protocol.CQRSAction])(implicit
  val system: ActorSystem[_]
) {

  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  def getSurgeons: Future[Set[Surgeon]] = commonController.ask(GetSurgeons)

  def getSurgeon(id: String): Future[Option[Surgeon]] =
    commonController.ask(GetSurgeon(id, _))

  def getAnesthetist(id: String): Future[Option[Anesthetist]] =
    commonController.ask(GetAnesthetist(id, _))

  def getGeneralPractitioner(id: String): Future[Option[GeneralPractitioner]] =
    commonController.ask(GetGeneralPractitioner(id, _))

  def getInstrumentalist(id: String): Future[Option[Instrumentalist]] =
    commonController.ask(GetInstrumentalist(id, _))

  def getRescuer(id: String): Future[Option[Rescuer]] =
    commonController.ask(GetRescuer(id, _))

  def getWardNurse(id: String): Future[Option[WardNurse]] =
    commonController.ask(GetWardNurse(id, _))

  def getPatient(id: String): Future[Option[Patient]] =
    commonController.ask(GetPatient(id, _))

  val commonRoutes: Route =
    pathPrefix("api") {
      pathPrefix("surgeons") {
        path(Segment) { id =>
          get {
            headerValueByName("x-access-token") { value =>
              authorize(hasCommonPermissions(value)) {
                onSuccess(getSurgeon(id)) { response =>
                  complete(StatusCodes.Accepted, response)
                }
              }
            }
          }
        }
      } ~
        pathPrefix("anesthetists") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getAnesthetist(id)) { response =>
                    complete(StatusCodes.Accepted, response)
                  }
                }
              }
            }
          }
        } ~
        pathPrefix("generalpractitioners") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getGeneralPractitioner(id)) { response =>
                    complete(StatusCodes.Accepted, response)
                  }
                }
              }
            }
          }
        } ~
        pathPrefix("instrumentalists") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getInstrumentalist(id)) { response =>
                    complete(StatusCodes.Accepted, response)
                  }
                }
              }
            }
          }
        } ~
        pathPrefix("rescuers") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getRescuer(id)) { response =>
                    complete(StatusCodes.Accepted, response)
                  }
                }
              }
            }
          }
        } ~
        pathPrefix("wardnurses") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getWardNurse(id)) { response =>
                    complete(StatusCodes.Accepted, response)
                  }
                }
              }
            }
          }
        } ~
        pathPrefix("patients") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasCommonPermissions(value)) {
                  onSuccess(getPatient(id)) { response =>
                    complete(StatusCodes.OK, response)
                  }
                }
              }
            }
          }
        }
    }
}
