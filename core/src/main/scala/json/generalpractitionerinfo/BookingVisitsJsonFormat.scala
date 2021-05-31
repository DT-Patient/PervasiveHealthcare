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

import domainmodel.generalpractitionerinfo.BookingVisit
import domainmodel.generalpractitionerinfo.BookingVisitHistory.BookingVisitHistory
import json.LocalDateJsonFormat.DateFormat
import json.RequestJsonFormats.{IntJsonFormat, immSetFormat, jsonFormat4}
import json.generalpractitionerinfo.VisitJsonFormat.visitJsonFormat
import json.utility.InfoDescriptionJsonFormat._
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.RootJsonFormat

/**
 * Json marshalling for booking visit object.
 */
object BookingVisitsJsonFormat {

  /**
   * Implicit for booking visit object.
   */
  implicit val bookingVisitJsonFormat: RootJsonFormat[BookingVisit] =
    jsonFormat4(BookingVisit)

  /**
   * Implicit for booking visit history object.
   */
  implicit val bookingVisitHistoryJsonFormat
    : RootJsonFormat[BookingVisitHistory] = jsonFormat1(BookingVisitHistory)
}
