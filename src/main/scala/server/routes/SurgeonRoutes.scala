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
import domainmodel.medicalrecords.{MedicalRecord, MedicalRecordsID}
import json.RequestJsonFormats.acceptedJsonFormat
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordJsonFormat
import server.models.JwtAuthentication.hasHospitalPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class SurgeonRoutes(surgeonController: ActorRef[Protocol.Command])(implicit val system: ActorSystem[_]) {

  private implicit val timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  def insertMedicalRecord(medicalRecord: MedicalRecord): Future[Confirmation] =
    surgeonController.ask(InsertMedicalRecord(medicalRecord, _))

  def updateMedicalRecord(medicalRecordsID: MedicalRecordsID, medicalRecord: MedicalRecord): Future[Confirmation] =
    surgeonController.ask(UpdateMedicalRecord(medicalRecordsID, medicalRecord, _))

  val surgeonRoutes: Route =
    pathPrefix("api") {
      path("medicalrecords") {
        pathEnd {

          post {
            headerValueByName("x-access-token") { value =>
              authorize(hasHospitalPermissions(value)) {
                entity(as[MedicalRecord]) { medicalRecord =>
                  onSuccess(insertMedicalRecord(medicalRecord)) { response =>
                    response match {
                      case _: Accepted => complete(StatusCodes.Created, response)
                      case _: Rejected => complete(StatusCodes.BadRequest, response)
                    }
                  }
                }
              }
            }
          }
        } ~
          path(Segment) {
            id =>
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasHospitalPermissions(value)) {
                      entity(as[MedicalRecord]) { medicalRecord =>
                        onSuccess(updateMedicalRecord(MedicalRecordsID(id), medicalRecord)) { response =>
                          response match {
                            case _: Accepted => complete(StatusCodes.Created, response)
                            case _: Rejected => complete(StatusCodes.BadRequest, response)
                          }
                        }
                      }
                    }
                  }
                }
              )
          }
      } ~
        path("clinicaldiary") {
          path(Segment) {
            id =>
              put {
                headerValueByName("x-access-token") { value =>
                  authorize(hasHospitalPermissions(value)) {
                    entity(as[MedicalRecord]) { medicalRecord =>
                      onSuccess(updateMedicalRecord(MedicalRecordsID(id), medicalRecord)) { response =>
                        response match {
                          case _: Accepted => complete(StatusCodes.Created, response)
                          case _: Rejected => complete(StatusCodes.BadRequest, response)
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
