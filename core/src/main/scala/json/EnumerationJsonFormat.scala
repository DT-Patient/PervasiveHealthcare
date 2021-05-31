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

import json.RequestJsonFormats.IntJsonFormat
import spray.json.{
  DeserializationException,
  JsNumber,
  JsObject,
  JsString,
  JsValue,
  RootJsonFormat
}

/**
 * Json format for enumeration.
 */
object EnumerationJsonFormat {

  /**
   * Implicit class for enumeration object.
   *
   * @param enu , generic enumeration
   * @tparam T , generic type
   */
  implicit class EnumJsonConverter[T <: scala.Enumeration](enu: T)
    extends RootJsonFormat[T#Value] {

    override def write(obj: T#Value): JsValue =
      JsObject(
        "id" -> JsNumber(obj.id),
        "value" -> JsString(obj.toString.replace("_", " "))
      )

    override def read(json: JsValue): T#Value = {
      json match {
        case JsObject(obj) => enu(obj("id").convertTo[Int])
        case somethingElse =>
          throw DeserializationException(
            s"Expected a value from enum $enu instead of $somethingElse"
          )
      }
    }
  }
}
