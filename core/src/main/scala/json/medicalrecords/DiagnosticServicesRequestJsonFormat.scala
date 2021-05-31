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

import domainmodel.medicalrecords.DiagnosticServicesRequests.DiagnosticServicesRequests
import domainmodel.medicalrecords.{
  DiagnosticServicesRequest,
  DiagnosticServicesRequestID,
  Form
}
import json.IDJsonFormat.doctorIDJsonFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat4
}
import spray.json.RootJsonFormat

/**
 * Json format for diagnostic services request object.
 */
object DiagnosticServicesRequestJsonFormat {

  /**
   * Implicit for diagnostic services request ID object.
   */
  implicit val diagnosticServicesRequestIDJsonFormat
    : RootJsonFormat[DiagnosticServicesRequestID] = jsonFormat1(
    DiagnosticServicesRequestID
  )

  /**
   * Implicit for form object.
   */
  implicit val formJsonFormat: RootJsonFormat[Form] = jsonFormat1(Form)

  /**
   * Implicit for diagnostic services request object.
   */
  implicit val diagnosticServicesRequestJsonFormat
    : RootJsonFormat[DiagnosticServicesRequest] = jsonFormat4(
    DiagnosticServicesRequest
  )

  /**
   * Implicit for diagnostic services requests object.
   */
  implicit val diagnosticServicesRequestsJsonFormat
    : RootJsonFormat[DiagnosticServicesRequests] = jsonFormat1(
    DiagnosticServicesRequests
  )

}
