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

package json

import spray.json.{JsString, JsValue, RootJsonFormat, deserializationError}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Json format for local date time object
 */
object LocalDateTimeJsonFormat {

  /**
   * Implicit for local date time object.
   */
  implicit object DateTimeFormat extends RootJsonFormat[LocalDateTime] {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override def write(date: LocalDateTime): JsValue = {
      JsString(date.format(formatter))
    }

    override def read(json: JsValue): LocalDateTime =
      json match {
        case JsString(s) => LocalDateTime.parse(s, formatter)
        case error       => deserializationError(s"Expected JsString, got $error")
      }
  }
}
