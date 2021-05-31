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

package json.medicalrecords

import domainmodel.medicalrecords._
import json.LocalDateTimeJsonFormat.DateTimeFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat2
}
import spray.json.RootJsonFormat

/**
 * Json format for advice request object.
 */
object AdviceRequestJsonFormat {

  /**
   * Implicit for request object.
   */
  implicit val requestJsonFormat: RootJsonFormat[Request] = jsonFormat1(Request)

  /**
   * Implicit for advice request object.
   */
  implicit val adviceRequestJsonFormat: RootJsonFormat[AdviceRequest] =
    jsonFormat2(AdviceRequest)

}
