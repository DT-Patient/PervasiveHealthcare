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

import domainmodel.medicalrecords.clinicaldiary.ClinicalDiary
import json.medicalrecords.clinicaldiary.HealthEvolutionJsonFormat.healthEvolutionJsonFormat
import json.medicalrecords.clinicaldiary.TreatmentsJsonFormat.{
  diagnosticTreatmentsJsonFormat,
  rehabilitationTreatmentsJsonFormat,
  therapeuticTreatmentsJsonFormat
}
import spray.json.DefaultJsonProtocol.{jsonFormat4, optionFormat}
import spray.json.RootJsonFormat

/**
 * Json format for clinical diary object.
 */
object ClinicalDiaryJsonFormat {

  /**
   * Implicit for clinical diary object.
   */
  implicit val clinicalDiaryJsonFormat: RootJsonFormat[ClinicalDiary] =
    jsonFormat4(ClinicalDiary)
}
