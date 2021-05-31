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

import domainmodel.medicalrecords.MedicalRecordHistory.MedicalRecordHistory
import domainmodel.medicalrecords.{MedicalRecord, MedicalRecordsID}
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.RequestJsonFormats.immSetFormat
import json.medicalrecords.AdviceRequestJsonFormat.adviceRequestJsonFormat
import json.medicalrecords.AnesthesiologyRecordJsonFormat.anesthesiologyRecordJsonFormat
import json.medicalrecords.DiagnosticServicesRequestJsonFormat.diagnosticServicesRequestsJsonFormat
import json.medicalrecords.DischargeLetterJsonFormat.dischargeLetterJsonFormat
import json.medicalrecords.GraphicJsonFormat.graphicJsonFormat
import json.medicalrecords.MedicalSurgicalDevicesJsonFormat.medicalSurgicalDevicesJsonFormat
import json.medicalrecords.NursingDocumentationJsonFormat.nursingDocumentationJsonFormat
import json.medicalrecords.OperatingReportsJsonFormat.operatingReportsJsonFormat
import json.medicalrecords.PainreliefJsonFormat.painreliefHistoryJsonFormat
import json.medicalrecords.ReportsJsonFormat.reportsJsonFormat
import json.medicalrecords.SingleSheetTherapyJsonFormat.singleSheetTherapiesJsonFormat
import json.medicalrecords.clinicaldiary.ClinicalDiaryJsonFormat.clinicalDiaryJsonFormat
import json.medicalrecords.initialanalysis.InitialAnalysisJsonFormat.initialAnalysisJsonFormat
import spray.json.DefaultJsonProtocol.{
  BooleanJsonFormat,
  StringJsonFormat,
  jsonFormat1,
  jsonFormat17,
  optionFormat
}
import spray.json.RootJsonFormat

/**
 * Json format for medical record object.
 */
object MedicalRecordJsonFormat {

  /**
   * Implicit for medical records ID object.
   */
  implicit val medicalRecordsIDJsonFormat: RootJsonFormat[MedicalRecordsID] =
    jsonFormat1(MedicalRecordsID)

  /**
   * Implicit for medical record object.
   */
  implicit val medicalRecordJsonFormat: RootJsonFormat[MedicalRecord] =
    jsonFormat17(MedicalRecord)

  /**
   * Implicit for medical records object.
   */
  implicit val medicalRecordsJsonFormat: RootJsonFormat[MedicalRecordHistory] =
    jsonFormat1(MedicalRecordHistory)

}
