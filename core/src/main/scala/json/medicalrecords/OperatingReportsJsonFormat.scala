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

import domainmodel.medicalrecords.Medical.Medical
import domainmodel.medicalrecords._
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import json.professionalfigure.ProfessionalFigureJsonFormat._
import json.utility.InfoDescriptionJsonFormat.noteJsonFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat2,
  jsonFormat3,
  optionFormat
}
import spray.json.RootJsonFormat

/**
 * Json format for operating reports object.
 */
object OperatingReportsJsonFormat {

  /**
   * Implicit for surgeon report object.
   */
  implicit val surgeonReportJsonFormat: RootJsonFormat[SurgeonReport] =
    jsonFormat2(SurgeonReport)

  /**
   * Implicit for anesthetist report object.
   */
  implicit val anesthetistReportJsonFormat: RootJsonFormat[AnesthetistReport] =
    jsonFormat2(AnesthetistReport)

  /**
   * Implicit for instrumental report object.
   */
  implicit val instrumentalistReportJsonFormat
    : RootJsonFormat[InstrumentalistReport] = jsonFormat2(InstrumentalistReport)

  /**
   * Implicit for medical object.
   */
  implicit val medicalJsonFormat: RootJsonFormat[Medical] = jsonFormat3(Medical)

  /**
   * Implicit for intervention type object.
   */
  implicit val interventionTypeJsonFormat: RootJsonFormat[InterventionType] =
    jsonFormat1(InterventionType)

  /**
   * Implicit for operating reports object.
   */
  implicit val operatingReportsJsonFormat: RootJsonFormat[OperatingReports] =
    jsonFormat3(OperatingReports)

}
