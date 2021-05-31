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

import domainmodel.generalinfo.{GeneralInfo, Height, Weight}
import json.PathologyJsonFormat.previousPathologiesJsonFormat
import json.generalinfo.AllergyJsonFormat._
import json.generalinfo.BloodGroupJsonFormat.bloodGroupJsonFormat
import json.generalinfo.ExamJsonFormat.examHistoryJsonFormat
import json.generalinfo.PrescriptionJsonFormat.prescriptionHistoryJsonFormat
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * Json format for general info object.
 */
object GeneralInfoJsonFormat {

  import DefaultJsonProtocol._

  /**
   * Implicit for weight object.
   */
  implicit val weightJsonFormat: RootJsonFormat[Weight] = jsonFormat1(Weight)

  /**
   * Implicit for height object.
   */
  implicit val heightJsonFormat: RootJsonFormat[Height] = jsonFormat1(Height)

  /**
   * Implicit for general info object.
   */
  implicit val generalInfoJsonFormat: RootJsonFormat[GeneralInfo] = jsonFormat7(
    GeneralInfo
  )

}
