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

import domainmodel.medicalrecords.Painrelief
import domainmodel.medicalrecords.PainreliefHistory.PainreliefHistory
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{jsonFormat1, jsonFormat2}
import spray.json.RootJsonFormat

/**
 * Json format for pain relief object.
 */
object PainreliefJsonFormat {

  /**
   * Implicit for pain relief object.
   */
  implicit val painreliefJsonFormat: RootJsonFormat[Painrelief] = jsonFormat2(
    Painrelief
  )

  /**
   * Implicit for pain relief history object.
   */
  implicit val painreliefHistoryJsonFormat: RootJsonFormat[PainreliefHistory] =
    jsonFormat1(PainreliefHistory)

}
