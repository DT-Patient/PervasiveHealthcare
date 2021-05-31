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

package client.cardiologist

import akka.actor.ClassicActorSystemProvider
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import client.APITokenHeader
import domainmodel.{CardiologyVisit, DoctorID}
import json.CardiologyVisitJsonFormat.cardiologyVisitJsonFormat
import spray.json.enrichAny

import scala.concurrent.Future

object Requests {

  def allCardiologyVisitsRequest(token: String, doctorID: DoctorID)(implicit
    system: ClassicActorSystemProvider
  ): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"http://127.0.0.1:8080/api/cardiologyvisits/${doctorID.value}",
        headers = List(APITokenHeader(token)),
        entity = HttpEntity(ContentTypes.`application/json`, "")
      )
    )
  }

  def insertCardiologyVisitRequest(
    token: String,
    cardiologyVisit: CardiologyVisit
  )(implicit system: ClassicActorSystemProvider): Future[HttpResponse] = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://127.0.0.1:8080/api/cardiologyvisits",
        headers = List(APITokenHeader(token)),
        entity = HttpEntity(
          ContentTypes.`application/json`,
          s"""${cardiologyVisit.toJson}"""
        )
      )
    )
  }
}
