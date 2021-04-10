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
import domainmodel.PatientID
import domainmodel.generalinfo.GeneralInfo
import domainmodel.medicalrecords.{MedicalRecord, MedicalRecordsID}
import json.RequestJsonFormats.acceptedJsonFormat
import json.generalinfo.GeneralInfoJsonFormat.generalInfoJsonFormat
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordJsonFormat
import server.models.JwtAuthentication.hasDoctorPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class WardNurseRoutes(wardnurseController: ActorRef[Protocol.Command])(implicit val system: ActorSystem[_]) {

  private implicit val timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  def insertMedicalRecord(medicalRecord: MedicalRecord): Future[Confirmation] =
    wardnurseController.ask(InsertMedicalRecord(medicalRecord, _))

  def updateMedicalRecord(medicalRecordID: MedicalRecordsID, medicalRecord: MedicalRecord): Future[Confirmation] =
    wardnurseController.ask(UpdateMedicalRecord(medicalRecordID, medicalRecord, _))

  def insertGeneralInfo(generalInfo: GeneralInfo): Future[Confirmation] =
    wardnurseController.ask(InsertGeneralInfo(generalInfo, _))

  def updateGeneralInfo(patientID: PatientID, generalInfo: GeneralInfo): Future[Confirmation] =
    wardnurseController.ask(UpdateGeneralInfo(patientID, generalInfo, _))

  val wardnurseRoutes: Route =
    pathPrefix("api") {
      pathPrefix("medicalrecords") {
        pathEnd {

          post {
            headerValueByName("x-access-token") { value =>
              authorize(hasDoctorPermissions(value)) {
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
                    authorize(hasDoctorPermissions(value)) {
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
        pathPrefix("clinicaldiary") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
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
                      authorize(hasDoctorPermissions(value)) {
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
        pathPrefix("generalinfo") {
          pathEnd {
            path(Segment) {
              id =>
                concat(
                  put {
                    headerValueByName("x-access-token") { value =>
                      authorize(hasDoctorPermissions(value)) {
                        entity(as[GeneralInfo]) { generalInfo =>
                          onSuccess(updateGeneralInfo(PatientID(id), generalInfo)) { response =>
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
          }
        }
    }
}
