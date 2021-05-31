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

package json.generalinfo

import domainmodel.generalinfo.{BloodGroup, BloodType, Rh}
import json.EnumerationJsonFormat.EnumJsonConverter
import spray.json.DefaultJsonProtocol.jsonFormat2
import spray.json.RootJsonFormat

/**
 * Json format for blood group object.
 */
object BloodGroupJsonFormat {

  /**
   * Implicit for blood type object.
   */
  implicit val bloodTypeJsonFormat: EnumJsonConverter[BloodType.type] =
    new EnumJsonConverter(BloodType)

  /**
   * Implicit for rh enumeration.
   */
  implicit val rhJsonFormat: EnumJsonConverter[Rh.type] = new EnumJsonConverter(
    Rh
  )

  /**
   * Implicit for blood group object.
   */
  implicit val bloodGroupJsonFormat: RootJsonFormat[BloodGroup] = jsonFormat2(
    BloodGroup
  )

}
