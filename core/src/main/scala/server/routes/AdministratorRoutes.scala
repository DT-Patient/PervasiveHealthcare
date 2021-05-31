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
import json.RequestJsonFormats.acceptedJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat._
import server.models.JwtAuthentication.hasAdminPermissions
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
 * This class contains the implementation of all the routes that the administrator can call up to insert or update elements in the db.
 *
 * @param administratorController administrator controller
 * @param system                  represent the actor system
 */
class AdministratorRoutes(
  administratorController: ActorRef[Protocol.CQRSAction]
)(implicit val system: ActorSystem[_]) {

  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  /**
   * Method to insert a surgeon in the db
   *
   * @param surgeon surgeon to insert
   * @return confirmation
   */
  def insertSurgeon(surgeon: Surgeon): Future[Confirmation] =
    administratorController.ask(InsertSurgeon(surgeon, _))

  /**
   * Method to update an existing surgeon in the db
   *
   * @param id      surgeon's id
   * @param surgeon surgeon updated
   * @return confirmation
   */
  def updateSurgeon(id: String, surgeon: Surgeon): Future[Confirmation] =
    administratorController.ask(UpdateSurgeon(id, surgeon, _))

  /**
   * Method to insert an anesthetist in the db
   *
   * @param anesthetist anesthetist to insert
   * @return confirmation
   */
  def insertAnesthetist(anesthetist: Anesthetist): Future[Confirmation] =
    administratorController.ask(InsertAnesthetist(anesthetist, _))

  /**
   * Method to update an existing anesthetist in the db
   *
   * @param id          anesthetist's id
   * @param anesthetist anesthetist updated
   * @return confirmation
   */
  def updateAnesthetist(
    id: String,
    anesthetist: Anesthetist
  ): Future[Confirmation] =
    administratorController.ask(UpdateAnesthetist(id, anesthetist, _))

  /**
   * Method to insert a general practitioner in the db
   *
   * @param generalPractitioner general practitioner to insert
   * @return confirmation
   */
  def insertGeneralPractitioner(
    generalPractitioner: GeneralPractitioner
  ): Future[Confirmation] =
    administratorController.ask(
      InsertGeneralPractitioner(generalPractitioner, _)
    )

  /**
   * Method to update an existing general practitioner in the db
   *
   * @param id                  general practitioner's id
   * @param generalPractitioner general practitioner updated
   * @return confirmation
   */
  def updateGeneralPractitioner(
    id: String,
    generalPractitioner: GeneralPractitioner
  ): Future[Confirmation] =
    administratorController.ask(
      UpdateGeneralPractitioner(id, generalPractitioner, _)
    )

  /**
   * Method to insert an instrumentalist in the db
   *
   * @param instrumentalist instrumentalist to insert
   * @return confirmation
   */
  def insertInstrumentalist(
    instrumentalist: Instrumentalist
  ): Future[Confirmation] =
    administratorController.ask(InsertInstrumentalist(instrumentalist, _))

  /**
   * Method to update an existing instrumentalist in the db
   *
   * @param id              instrumentalist's id
   * @param instrumentalist instrumentalist updated
   * @return confirmation
   */
  def updateInstrumentalist(
    id: String,
    instrumentalist: Instrumentalist
  ): Future[Confirmation] =
    administratorController.ask(UpdateInstrumentalist(id, instrumentalist, _))

  /**
   * Method to insert a rescuer in the db
   *
   * @param rescuer rescuer to insert
   * @return confirmation
   */
  def insertRescuer(rescuer: Rescuer): Future[Confirmation] =
    administratorController.ask(InsertRescuer(rescuer, _))

  /**
   * Method to update an existing rescuer in the db
   *
   * @param id      rescuer's id
   * @param rescuer rescuer updated
   * @return confirmation
   */
  def updateRescuer(id: String, rescuer: Rescuer): Future[Confirmation] =
    administratorController.ask(UpdateRescuer(id, rescuer, _))

  /**
   * Method to insert a ward nurse in the db
   *
   * @param wardNurse ward nurse to insert
   * @return confirmation
   */
  def insertWardNurse(wardNurse: WardNurse): Future[Confirmation] =
    administratorController.ask(InsertWardNurse(wardNurse, _))

  /**
   * Method to update an existing ward nurse in the db
   *
   * @param id        ward nurse's id
   * @param wardNurse ward nurse updated
   * @return confirmation
   */
  def updateWardNurse(id: String, wardNurse: WardNurse): Future[Confirmation] =
    administratorController.ask(UpdateWardNurse(id, wardNurse, _))

  /**
   * Method to insert a cardiologist in the db
   *
   * @param cardiologist cardiologist to insert
   * @return confirmation
   */
  def insertCardiologist(cardiologist: Cardiologist): Future[Confirmation] =
    administratorController.ask(InsertCardiologist(cardiologist, _))

  /**
   * Method to update an existing cardiologist in the db
   *
   * @param id           cardiologist's id
   * @param cardiologist cardiologist updated
   * @return confirmation
   */
  def updateCardiologist(
    id: String,
    cardiologist: Cardiologist
  ): Future[Confirmation] =
    administratorController.ask(UpdateCardiologist(id, cardiologist, _))

  /**
   * Method to insert a patient in the db
   *
   * @param patient patient to insert
   * @return confirmation
   */
  def insertPatient(patient: Patient): Future[Confirmation] =
    administratorController.ask(InsertPatient(patient, _))

  /**
   * Method to update an existing patient in the db
   *
   * @param id      patient's id
   * @param patient patient updated
   * @return confirmation
   */
  def updatePatient(id: String, patient: Patient): Future[Confirmation] =
    administratorController.ask(UpdatePatient(id, patient, _))

  val administratorRoutes: Route =
    pathPrefix("api") {
      pathPrefix("surgeons") {
        pathEnd {
          post {
            headerValueByName("x-access-token") { value =>
              authorize(hasAdminPermissions(value)) {
                entity(as[Surgeon]) { surgeon =>
                  onSuccess(insertSurgeon(surgeon)) { response =>
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
        } ~ path(Segment) { id =>
          put {
            headerValueByName("x-access-token") { value =>
              authorize(hasAdminPermissions(value)) {
                entity(as[Surgeon]) { surgeon =>
                  onSuccess(updateSurgeon(id, surgeon)) { response =>
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
      } ~
        pathPrefix("anesthetists") {
          pathEnd {

            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[Anesthetist]) { anesthetist =>
                    onSuccess(insertAnesthetist(anesthetist)) { response =>
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
                    authorize(hasAdminPermissions(value)) {
                      entity(as[Anesthetist]) { anesthetist =>
                        onSuccess(updateAnesthetist(id, anesthetist)) {
                          response =>
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
        pathPrefix("generalpractitioners") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[GeneralPractitioner]) { generalPractitioner =>
                    onSuccess(insertGeneralPractitioner(generalPractitioner)) {
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasAdminPermissions(value)) {
                      entity(as[GeneralPractitioner]) { generalPractitioner =>
                        onSuccess(
                          updateGeneralPractitioner(id, generalPractitioner)
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
              )
            }
        } ~
        pathPrefix("instrumentalists") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[Instrumentalist]) { instrumentalist =>
                    onSuccess(insertInstrumentalist(instrumentalist)) {
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
              concat(
                put {
                  headerValueByName("x-access-token") { value =>
                    authorize(hasAdminPermissions(value)) {
                      entity(as[Instrumentalist]) { instrumentalist =>
                        onSuccess(updateInstrumentalist(id, instrumentalist)) {
                          response =>
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
        pathPrefix("rescuers") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[Rescuer]) { rescuer =>
                    onSuccess(insertRescuer(rescuer)) { response =>
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
                    authorize(hasAdminPermissions(value)) {
                      entity(as[Rescuer]) { rescuer =>
                        onSuccess(updateRescuer(id, rescuer)) { response =>
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
              )
            }
        } ~
        pathPrefix("wardnurses") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[WardNurse]) { wardnurse =>
                    onSuccess(insertWardNurse(wardnurse)) { response =>
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
              put {
                headerValueByName("x-access-token") { value =>
                  authorize(hasAdminPermissions(value)) {
                    entity(as[WardNurse]) { wardnurse =>
                      onSuccess(updateWardNurse(id, wardnurse)) { response =>
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
        } ~
        pathPrefix("cardiologists") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[Cardiologist]) { cardiologist =>
                    onSuccess(insertCardiologist(cardiologist)) { response =>
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
              put {
                headerValueByName("x-access-token") { value =>
                  authorize(hasAdminPermissions(value)) {
                    entity(as[Cardiologist]) { cardiologist =>
                      onSuccess(updateCardiologist(id, cardiologist)) {
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
        } ~
        pathPrefix("patients") {
          pathEnd {
            post {
              headerValueByName("x-access-token") { value =>
                authorize(hasAdminPermissions(value)) {
                  entity(as[Patient]) { patient =>
                    onSuccess(insertPatient(patient)) { response =>
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
                    authorize(hasAdminPermissions(value)) {
                      entity(as[Patient]) { patient =>
                        onSuccess(updatePatient(id, patient)) { response =>
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
              )
            }
        }
    }
}
