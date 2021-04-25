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
import server.models.JwtAuthentication.hasRescuerPermissions
import server.models.Protocol
import server.models.Protocol._
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordJsonFormat

/**
 * This class contains the implementation of all the routes that the rescuer can call up to insert or update elements in the db.
 *
 * @param rescuerController rescuer controller
 * @param system            represent the actor system
 */
class RescuerRoutes(rescuerController: ActorRef[Protocol.CQRSAction])(implicit val system: ActorSystem[_]) {

  private implicit val timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  /**
   * Method to update an existing medical record in the db
   *
   * @param medicalRecordsID medical record's id
   * @param medicalRecord    medical record updated
   * @return confirmation
   */
  def updateMedicalRecord(medicalRecordsID: MedicalRecordsID, medicalRecord: MedicalRecord): Future[Confirmation] =
    rescuerController.ask(UpdateMedicalRecord(medicalRecordsID, medicalRecord, _))

  val rescuerRoutes: Route =
    pathPrefix("api") {
      pathPrefix("medicalrecords") {
        path(Segment) {
          id =>
            concat(
              put {
                headerValueByName("x-access-token") { (value) =>
                  authorize(hasRescuerPermissions(value)) {
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
      }
    }
}