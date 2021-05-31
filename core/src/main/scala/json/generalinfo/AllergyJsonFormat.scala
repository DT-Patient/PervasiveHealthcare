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

import domainmodel.generalinfo.Allergies.Allergies
import domainmodel.generalinfo.{Allergy, AllergyClass, AllergyDescription}
import json.EnumerationJsonFormat.EnumJsonConverter
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  immSetFormat,
  jsonFormat1,
  jsonFormat2
}
import spray.json.RootJsonFormat

/**
 * Json format for allergy object.
 */
object AllergyJsonFormat {

  /**
   * Implicit for allergy enumeration.
   */
  implicit val allergyClassJsonFormat: EnumJsonConverter[AllergyClass.type] =
    new EnumJsonConverter(AllergyClass)

  /**
   * Implicit for allergy description object.
   */
  implicit val allergyDescriptionJsonFormat
    : RootJsonFormat[AllergyDescription] = jsonFormat1(AllergyDescription)

  /**
   * Implicit for allergy object.
   */
  implicit val allergyJsonFormat: RootJsonFormat[Allergy] = jsonFormat2(Allergy)

  /**
   * Implicit for allergies object.
   */
  implicit val allergiesJsonFormat: RootJsonFormat[Allergies] = jsonFormat1(
    Allergies
  )
}
