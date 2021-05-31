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

package json.generalpractitionerinfo

import domainmodel.generalpractitionerinfo.TherapyHistory.TherapyHistory
import domainmodel.generalpractitionerinfo._
import json.LocalDateJsonFormat.DateFormat
import json.RequestJsonFormats.{
  StringJsonFormat,
  immSetFormat,
  jsonFormat4,
  optionFormat
}
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.RootJsonFormat

/**
 * Json format for therapy object.
 */
object TherapyJsonFormat {

  /**
   * Implicit for therapy date object.
   */
  implicit val therapyDateJsonFormat: RootJsonFormat[TherapyDate] = jsonFormat1(
    TherapyDate
  )

  /**
   * Implicit for therapy description object.
   */
  implicit val therapyDescriptionJsonFormat
    : RootJsonFormat[TherapyDescription] = jsonFormat1(TherapyDescription)

  /**
   * Implicit for therapy initial date object.
   */
  implicit val therapyInitialDateJsonFormat
    : RootJsonFormat[TherapyInitialDate] = jsonFormat1(TherapyInitialDate)

  /**
   * Implicit for therapy final date object.
   */
  implicit val therapyFinalDateJsonFormat: RootJsonFormat[TherapyFinalDate] =
    jsonFormat1(TherapyFinalDate)

  /**
   * Implicit for therapy object.
   */
  implicit val therapyJsonFormat: RootJsonFormat[Therapy] = jsonFormat4(Therapy)

  /**
   * Implicit for therapies object.
   */
  implicit val therapiesJsonFormat: RootJsonFormat[TherapyHistory] =
    jsonFormat1(TherapyHistory)
}
