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

import spray.json.{DeserializationException, JsNumber, JsValue, RootJsonFormat}

/**
 * Json format for enumeration.
 */
object EnumerationJsonFormat {

  /**
   * Implicit class for enumeration object.
   *
   * @param enu, generic enumeration
   * @tparam T, generic type
   */
  implicit class EnumJsonConverter[T <: scala.Enumeration](enu: T) extends RootJsonFormat[T#Value] {
    override def write(obj: T#Value): JsValue = JsNumber(obj.id)

    override def read(json: JsValue): T#Value = {
      json match {
        case JsNumber(txt) => enu.withName(enu.values.toList(txt.intValue()).toString)
        case somethingElse => throw DeserializationException(s"Expected a value from enum $enu instead of $somethingElse")
      }
    }
  }
}
