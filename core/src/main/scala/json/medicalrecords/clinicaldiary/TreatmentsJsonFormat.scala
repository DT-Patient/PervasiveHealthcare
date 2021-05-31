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

package json.medicalrecords.clinicaldiary

import domainmodel.medicalrecords.clinicaldiary.DiagnosticTreatments.DiagnosticTreatments
import domainmodel.medicalrecords.clinicaldiary.RehabilitationTreatments.RehabilitationTreatments
import domainmodel.medicalrecords.clinicaldiary.TherapeuticTreatments.TherapeuticTreatments
import domainmodel.medicalrecords.clinicaldiary._
import json.IDJsonFormat.doctorIDJsonFormat
import json.LocalDateJsonFormat.DateFormat
import json.RequestJsonFormats.immSetFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{jsonFormat1, jsonFormat3}
import spray.json.RootJsonFormat

/**
 * Json format for treatments object.
 */
object TreatmentsJsonFormat {

  /**
   * Implicit for treatment object.
   */
  implicit val treatmentJsonFormat: RootJsonFormat[Treatment] = jsonFormat3(
    Treatment
  )

  /**
   * Implicit for diagnostic treatment object.
   */
  implicit val diagnosticTreatmentJsonFormat
    : RootJsonFormat[DiagnosticTreatment] = jsonFormat1(DiagnosticTreatment)

  /**
   * Implicit for therapeutic treatment object.
   */
  implicit val therapeuticTreatmentJsonFormat
    : RootJsonFormat[TherapeuticTreatment] = jsonFormat1(TherapeuticTreatment)

  /**
   * Implicit for rehabilitation treatment object.
   */
  implicit val rehabilitationTreatmentJsonFormat
    : RootJsonFormat[RehabilitationTreatment] = jsonFormat1(
    RehabilitationTreatment
  )

  /**
   * Implicit for diagnostic treatments object.
   */
  implicit val diagnosticTreatmentsJsonFormat
    : RootJsonFormat[DiagnosticTreatments] = jsonFormat1(DiagnosticTreatments)

  /**
   * Implicit for therapeutic treatments object.
   */
  implicit val therapeuticTreatmentsJsonFormat
    : RootJsonFormat[TherapeuticTreatments] = jsonFormat1(TherapeuticTreatments)

  /**
   * Implicit for rehabilitation treatments object.
   */
  implicit val rehabilitationTreatmentsJsonFormat
    : RootJsonFormat[RehabilitationTreatments] = jsonFormat1(
    RehabilitationTreatments
  )
}
