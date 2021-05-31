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

import domainmodel.medicalrecords.Reports.Reports
import domainmodel.medicalrecords._
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat3,
  jsonFormat5
}
import spray.json.RootJsonFormat

/**
 * Json format for reports object
 */
object ReportsJsonFormat {

  /**
   * Implicit for diagnostics object.
   */
  implicit val diagnosticsJsonFormat: RootJsonFormat[Diagnostics] = jsonFormat1(
    Diagnostics
  )

  /**
   * Implicit for consulting object.
   */
  implicit val consultingJsonFormat: RootJsonFormat[Consulting] = jsonFormat1(
    Consulting
  )

  /**
   * Implicit for therapeutic delivery object.
   */
  implicit val therapeuticDeliveryJsonFormat
    : RootJsonFormat[TherapeuticDelivery] = jsonFormat1(TherapeuticDelivery)

  /**
   * Implicit for rehabilitation object.
   */
  implicit val rehabilitationJsonFormat: RootJsonFormat[Rehabilitation] =
    jsonFormat1(Rehabilitation)

  /**
   * Implicit for assistance type object.
   */
  implicit val assistanceTypeJsonFormat: RootJsonFormat[Assistance] =
    jsonFormat1(Assistance)

  /**
   * Implicit for activity object.
   */
  implicit val activityJsonFormat: RootJsonFormat[Activity] = jsonFormat5(
    Activity
  )

  /**
   * Implicit for treatment type object.
   */
  implicit val treatmentTypeJsonFormat: RootJsonFormat[TreatmentType] =
    jsonFormat1(TreatmentType)

  /**
   * Implicit for report object.
   */
  implicit val reportJsonFormat: RootJsonFormat[Report] = jsonFormat3(Report)

  /**
   * Implicit for reports object.
   */
  implicit val reportsJsonFormat: RootJsonFormat[Reports] = jsonFormat1(Reports)
}
