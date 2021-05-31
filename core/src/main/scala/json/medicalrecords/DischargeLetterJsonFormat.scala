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

import domainmodel.medicalrecords.PharmacologicalTherapies.PharmacologicalTherapies
import domainmodel.medicalrecords._
import json.IDJsonFormat.doctorIDJsonFormat
import json.LocalDateJsonFormat.DateFormat
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat5,
  jsonFormat7
}
import spray.json.RootJsonFormat

/**
 * Json format for discharge letter object.
 */
object DischargeLetterJsonFormat {

  /**
   * Implicit for admission reason object.
   */
  implicit val admissionReasonJsonFormat: RootJsonFormat[AdmissionReason] =
    jsonFormat1(AdmissionReason)

  /**
   * Implicit for clinical problems encountered object.
   */
  implicit val clinicalProblemsEncounteredJsonFormat
    : RootJsonFormat[ClinicalProblemsEncountered] = jsonFormat1(
    ClinicalProblemsEncountered
  )

  /**
   * Implicit for significant findings object.
   */
  implicit val significantFindingsJsonFormat
    : RootJsonFormat[SignificantFindings] = jsonFormat1(SignificantFindings)

  /**
   * Implicit for discharge diagnosis object.
   */
  implicit val dischargeDiagnosisJsonFormat
    : RootJsonFormat[DischargeDiagnosis] = jsonFormat1(DischargeDiagnosis)

  /**
   * Implicit for follow up instructions object.
   */
  implicit val followUpInstructionsJsonFormat
    : RootJsonFormat[FollowUpInstructions] = jsonFormat1(FollowUpInstructions)

  /**
   * Implicit for post hospital care information object.
   */
  implicit val postHospitalCareInformationJsonFormat
    : RootJsonFormat[PostHospitalCareInformation] = jsonFormat1(
    PostHospitalCareInformation
  )

  /**
   * Implicit for sdo compilation object.
   */
  implicit val sdoCompilationJsonFormat: RootJsonFormat[SdoCompilation] =
    jsonFormat1(SdoCompilation)

  /**
   * Implicit for pharmacological therapy object.
   */
  implicit val pharmacologicalTherapyJsonFormat
    : RootJsonFormat[PharmacologicalTherapy] = jsonFormat5(
    PharmacologicalTherapy
  )

  /**
   * Implicit for pharmacological therapies object.
   */
  implicit val pharmacologicalTherapiesJsonFormat
    : RootJsonFormat[PharmacologicalTherapies] = jsonFormat1(
    PharmacologicalTherapies
  )

  /**
   * Implicit for discharge letter object.
   */
  implicit val dischargeLetterJsonFormat: RootJsonFormat[DischargeLetter] =
    jsonFormat7(DischargeLetter)

}
