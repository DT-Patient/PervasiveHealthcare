/*
 * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *
 *                              Licensed under the Apache License, Version 2.0 (the "License");
 *                              you may not use this file except in compliance with the License.
 *                              You may obtain a copy of the License at
 *
 *                                  http://www.apache.org/licenses/LICENSE-2.0
 *
 *                              Unless required by applicable law or agreed to in writing, software
 *                              distributed under the License is distributed on an "AS IS" BASIS,
 *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *                              See the License for the specific language governing permissions and
 *                              limitations under the License.
 */

package client.surgeon

import akka.actor.ClassicActorSystemProvider
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import client.APITokenHeader
import domainmodel.DoctorID
import domainmodel.medicalrecords.MedicalRecord

import scala.concurrent.Future

object Requests {


  /**
   *
   * @param token         , token for authentication.
   * @param medicalRecord , medical record.
   * @param system        , actor system provider.
   * @return http response of patient
   */
  def insertMedicalRecordRequest(token: String, medicalRecord: MedicalRecord)(implicit system: ClassicActorSystemProvider): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = "http://127.0.0.1:8080/api/medicalrecords",
        headers = List(APITokenHeader(token)),
        entity = HttpEntity(ContentTypes.`application/json`, s"""$medicalRecord"""),
      )
    )
  }

  /**
   *
   * @param token         , token for authentication.
   * @param medicalRecord , medical record.
   * @param system        , actor system provider.
   * @return http response of patient
   */
  def updateMedicalRecordRequest(token: String, medicalRecord: MedicalRecord)(implicit system: ClassicActorSystemProvider): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.PUT,
        uri = s"http://127.0.0.1:8080/api/medicalrecords/${medicalRecord.medicalRecordID.value}",
        headers = List(APITokenHeader(token)),
        entity = HttpEntity(ContentTypes.`application/json`, s"""$medicalRecord"""),
      )
    )
  }

  /**
   *
   * @param token    , token for authentication.
   * @param doctorID , id of doctor.
   * @param system   , actor system provider.
   * @return http response of patient
   */
  def allMedicalRecordsRequest(token: String, doctorID: DoctorID)(implicit system: ClassicActorSystemProvider): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"http://127.0.0.1:8080/api/medicalrecords",
        headers = List(APITokenHeader(token)),
        entity = HttpEntity(ContentTypes.`application/json`, s"""$doctorID"""),
      )
    )
  }

}
