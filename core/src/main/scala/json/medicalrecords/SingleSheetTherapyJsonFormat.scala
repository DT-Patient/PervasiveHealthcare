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

import domainmodel.medicalrecords.SingleSheetTherapies.SingleSheetTherapies
import domainmodel.medicalrecords.{DrugsAdministered, DrugsPrescription}
import json.IDJsonFormat.doctorIDJsonFormat
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{jsonFormat2, jsonFormat3}
import spray.json.RootJsonFormat

/**
 * Json format for single sheet therapy object.
 */
object SingleSheetTherapyJsonFormat {

  /**
   * Implicit for drugs prescription object.
   */
  implicit val drugsPrescriptionJsonFormat: RootJsonFormat[DrugsPrescription] =
    jsonFormat3(DrugsPrescription)

  /**
   * Implicit for drug administered therapy object.
   */
  implicit val drugsAdministeredTherapyJsonFormat
    : RootJsonFormat[DrugsAdministered] = jsonFormat3(DrugsAdministered)

  /**
   * Implicit for single sheet therapies object.
   */
  implicit val singleSheetTherapiesJsonFormat
    : RootJsonFormat[SingleSheetTherapies] = jsonFormat2(SingleSheetTherapies)
}
