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

import domainmodel.generalinfo.PrescriptionHistory.PrescriptionHistory
import domainmodel.generalinfo.{
  Prescription,
  PrescriptionDate,
  PrescriptionInfo
}
import json.LocalDateJsonFormat.DateFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  immSetFormat,
  jsonFormat1,
  jsonFormat2
}
import spray.json.RootJsonFormat

/**
 * Json format for prescription object.
 */
object PrescriptionJsonFormat {

  /**
   * Implicit for prescription date object.
   */
  implicit val prescriptionDateJsonFormat: RootJsonFormat[PrescriptionDate] =
    jsonFormat1(PrescriptionDate)

  /**
   * Implicit for prescription info object.
   */
  implicit val prescriptionInfoJsonFormat: RootJsonFormat[PrescriptionInfo] =
    jsonFormat1(PrescriptionInfo)

  /**
   * Implicit for prescription object.
   */
  implicit val prescriptionJsonFormat: RootJsonFormat[Prescription] =
    jsonFormat2(Prescription)

  /**
   * Implicit for prescription history object.
   */
  implicit val prescriptionHistoryJsonFormat
    : RootJsonFormat[PrescriptionHistory] = jsonFormat1(PrescriptionHistory)
}
