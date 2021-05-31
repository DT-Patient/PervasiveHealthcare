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

import domainmodel.medicalrecords.VitalSigns.VitalSigns
import domainmodel.medicalrecords.{Graphic, VitalSign}
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.infoJsonFormat
import spray.json.DefaultJsonProtocol.{jsonFormat1, jsonFormat2}
import spray.json.RootJsonFormat

/**
 * Json format for graphic object.
 */
object GraphicJsonFormat {

  /**
   * Implicit for vital sign object.
   */
  implicit val vitalSignJsonFormat: RootJsonFormat[VitalSign] = jsonFormat2(
    VitalSign
  )

  /**
   * Implicit for vista signs object.
   */
  implicit val vitalSignsJsonFormat: RootJsonFormat[VitalSigns] = jsonFormat1(
    VitalSigns
  )

  /**
   * Implicit for graphic object.
   */
  implicit val graphicJsonFormat: RootJsonFormat[Graphic] = jsonFormat1(Graphic)

}
