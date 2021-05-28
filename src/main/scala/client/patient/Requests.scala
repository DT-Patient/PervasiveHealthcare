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

package client.patient

import akka.actor.ClassicActorSystemProvider
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import client.APITokenHeader
import domainmodel.PatientID

import scala.concurrent.Future

object Requests {

  /**
   * Patient request.
   *
   * @param token     , token for authentication.
   * @param patientID , id of patient.
   * @param system    , actor system provider.
   * @return http response of patient
   */
  def patientRequest(token: String, patientID: PatientID)(implicit system: ClassicActorSystemProvider): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        headers = List(APITokenHeader(token)),
        uri = s"http://127.0.0.1:8080/api/patients/${patientID.value}",
        entity = HttpEntity(ContentTypes.`application/json`, ""),
      )
    )
  }

}
