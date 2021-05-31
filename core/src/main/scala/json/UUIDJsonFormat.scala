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

import java.util.UUID

/**
 * Json format for UUID object
 */
object UUIDJsonFormat {

  /**
   * * Implicit for UUID object.
   */
  implicit object UUIDJsonFormat extends RootJsonFormat[UUID] {

    override def write(uuid: UUID): JsValue = JsString(uuid.toString)

    override def read(json: JsValue): UUID =
      json match {
        case JsString(uuid) => UUID.fromString(uuid)
        case error          => deserializationError(s"Expected UUID, got $error")
      }
  }
}
