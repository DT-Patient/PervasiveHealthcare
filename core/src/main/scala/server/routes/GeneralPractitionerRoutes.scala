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
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.{CardiologyPrediction, DoctorID, PatientID}
import json.CardiologyPredictionJsonFormat.cardiologyPredictionJsonFormat
import json.RequestJsonFormats.{acceptedJsonFormat, immSetFormat, seqFormat}
import json.generalpractitionerinfo.GeneralPractitionerInfoJsonFormat.generalPractitionerInfoJsonFormat
import server.models.JwtAuthentication.hasDoctorPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
 * This class contains the implementation of all the routes that the general practitioner can call up to insert or update elements in the db.
 *
 * @param generalPractitionerController general practitioner controller
 * @param system                        represent the actor system
 */
class GeneralPractitionerRoutes(
  generalPractitionerController: ActorRef[Protocol.CQRSAction]
)(implicit val system: ActorSystem[_]) {

  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  /**
   * Method to insert general practitioner info in the db
   *
   * @param generalPractitionerInfo general practitioner info to insert
   * @return confirmation
   */
  def insertGeneralPractitionerInfo(
    generalPractitionerInfo: GeneralPractitionerInfo
  ): Future[Confirmation] =
    generalPractitionerController.ask(
      InsertGeneralPractitionerInfo(generalPractitionerInfo, _)
    )

  /**
   * Method to update an existing general practitioner info in the db
   *
   * @param patientID               patient's id
   * @param generalPractitionerInfo general practitioner info updated
   * @return confirmation
   */
  def updateGeneralPractitionerInfo(
    patientID: PatientID,
    generalPractitionerInfo: GeneralPractitionerInfo
  ): Future[Confirmation] =
    generalPractitionerController.ask(
      UpdateGeneralPractitionerInfo(patientID, generalPractitionerInfo, _)
    )

  /**
   * Method to obtain all the general practitioners info in the db
   *
   * @param doctorID doctor's id
   * @return the general practitioner info
   */
  def getGeneralPractitionerInfo(
    doctorID: DoctorID
  ): Future[Set[GeneralPractitionerInfo]] =
    generalPractitionerController.ask(GetGeneralPractitionerInfo(doctorID, _))

  /**
   * Method to obtain all cardiology predictions in the db
   *
   * @param doctorID doctor's id
   * @return the cardiology predictions
   */
  def getCardiologyPredictions(
    doctorID: DoctorID
  ): Future[Seq[CardiologyPrediction]] =
    generalPractitionerController.ask(GetCardiologyPredictions(doctorID, _))

  /**
   * Method to update an existing cardiology predictions in the db
   *
   * @param doctorID doctor's id
   * @return confirmation
   */
  def updateCardiologyPredictions(doctorID: DoctorID): Future[Confirmation] =
    generalPractitionerController.ask(UpdateCardiologyPredictions(doctorID, _))

  val generalPractitionerRoutes: Route =
    pathPrefix("api") {
      pathPrefix("generalpractitionerinfo") {
        pathEnd {
          post {
            headerValueByName("x-access-token") { value =>
              authorize(hasDoctorPermissions(value)) {
                entity(as[GeneralPractitionerInfo]) { generalPractitionerInfo =>
                  onSuccess(
                    insertGeneralPractitionerInfo(generalPractitionerInfo)
                  ) { response =>
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
            concat(
              put {
                headerValueByName("x-access-token") { value =>
                  authorize(hasDoctorPermissions(value)) {
                    entity(as[GeneralPractitionerInfo]) {
                      generalPractitionerInfo =>
                        onSuccess(
                          updateGeneralPractitionerInfo(
                            PatientID(id),
                            generalPractitionerInfo
                          )
                        ) { response =>
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
              },
              get {
                headerValueByName("x-access-token") { value =>
                  authorize(hasDoctorPermissions(value)) {
                    onSuccess(getGeneralPractitionerInfo(DoctorID(id))) {
                      response => complete(StatusCodes.OK, response)
                    }
                  }
                }
              }
            )
          }
      } ~
        pathPrefix("visits") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  entity(as[GeneralPractitionerInfo]) {
                    generalPractitionerInfo =>
                      onSuccess(
                        insertGeneralPractitionerInfo(generalPractitionerInfo)
                      ) { response =>
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasDoctorPermissions(value)) {
                      entity(as[GeneralPractitionerInfo]) {
                        generalPractitionerInfo =>
                          onSuccess(
                            updateGeneralPractitionerInfo(
                              PatientID(id),
                              generalPractitionerInfo
                            )
                          ) { response =>
                            response match {
                              case _: Accepted =>
                                complete(StatusCodes.Created, response)
                              case _: Rejected =>
                                complete(StatusCodes.BadRequest, response)
                              case _ =>
                                complete(StatusCodes.BadRequest, response)
                            }
                          }
                      }
                    }
                  }
                }
              )
            }
        } ~
        pathPrefix("anamnesis") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  entity(as[GeneralPractitionerInfo]) {
                    generalPractitionerInfo =>
                      onSuccess(
                        insertGeneralPractitionerInfo(generalPractitionerInfo)
                      ) { response =>
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasDoctorPermissions(value)) {
                      entity(as[GeneralPractitionerInfo]) {
                        generalPractitionerInfo =>
                          onSuccess(
                            updateGeneralPractitionerInfo(
                              PatientID(id),
                              generalPractitionerInfo
                            )
                          ) { response =>
                            response match {
                              case _: Accepted =>
                                complete(StatusCodes.Created, response)
                              case _: Rejected =>
                                complete(StatusCodes.BadRequest, response)
                              case _ =>
                                complete(StatusCodes.BadRequest, response)
                            }
                          }
                      }
                    }
                  }
                }
              )
            }
        } ~
        pathPrefix("therapies") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  entity(as[GeneralPractitionerInfo]) {
                    generalPractitionerInfo =>
                      onSuccess(
                        insertGeneralPractitionerInfo(generalPractitionerInfo)
                      ) { response =>
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasDoctorPermissions(value)) {
                      entity(as[GeneralPractitionerInfo]) {
                        generalPractitionerInfo =>
                          onSuccess(
                            updateGeneralPractitionerInfo(
                              PatientID(id),
                              generalPractitionerInfo
                            )
                          ) { response =>
                            response match {
                              case _: Accepted =>
                                complete(StatusCodes.Created, response)
                              case _: Rejected =>
                                complete(StatusCodes.BadRequest, response)
                              case _ =>
                                complete(StatusCodes.BadRequest, response)
                            }
                          }
                      }
                    }
                  }
                }
              )
            }
        } ~
        pathPrefix("prescriptions") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  entity(as[GeneralPractitionerInfo]) {
                    generalPractitionerInfo =>
                      onSuccess(
                        insertGeneralPractitionerInfo(generalPractitionerInfo)
                      ) { response =>
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasDoctorPermissions(value)) {
                      entity(as[GeneralPractitionerInfo]) {
                        generalPractitionerInfo =>
                          onSuccess(
                            updateGeneralPractitionerInfo(
                              PatientID(id),
                              generalPractitionerInfo
                            )
                          ) { response =>
                            response match {
                              case _: Accepted =>
                                complete(StatusCodes.Created, response)
                              case _: Rejected =>
                                complete(StatusCodes.BadRequest, response)
                              case _ =>
                                complete(StatusCodes.BadRequest, response)
                            }
                          }
                      }
                    }
                  }
                }
              )
            }
        } ~
        pathPrefix("medicalcertificates") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  entity(as[GeneralPractitionerInfo]) {
                    generalPractitionerInfo =>
                      onSuccess(
                        insertGeneralPractitionerInfo(generalPractitionerInfo)
                      ) { response =>
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasDoctorPermissions(value)) {
                      entity(as[GeneralPractitionerInfo]) {
                        generalPractitionerInfo =>
                          onSuccess(
                            updateGeneralPractitionerInfo(
                              PatientID(id),
                              generalPractitionerInfo
                            )
                          ) { response =>
                            response match {
                              case _: Accepted =>
                                complete(StatusCodes.Created, response)
                              case _: Rejected =>
                                complete(StatusCodes.BadRequest, response)
                              case _ =>
                                complete(StatusCodes.BadRequest, response)
                            }
                          }
                      }
                    }
                  }
                }
              )
            }
        } ~
        pathPrefix("cardiologypredictions") {
          path(Segment) { id =>
            get {
              headerValueByName("x-access-token") { value =>
                authorize(hasDoctorPermissions(value)) {
                  onSuccess(getCardiologyPredictions(DoctorID(id))) {
                    response => complete(StatusCodes.OK, response)
                  }
                }
              }
            } ~
              put {
                headerValueByName("x-access-token") { value =>
                  authorize(hasDoctorPermissions(value)) {
                    onSuccess(updateCardiologyPredictions(DoctorID(id))) {
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

        }
    }
}
