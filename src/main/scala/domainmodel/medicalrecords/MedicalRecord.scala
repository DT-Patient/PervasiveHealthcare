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

package domainmodel.medicalrecords

import domainmodel.medicalrecords.DiagnosticServicesRequests.DiagnosticServicesRequests
import domainmodel.medicalrecords.MedicalSurgicalDevices.MedicalSurgicalDevices
import domainmodel.medicalrecords.PainreliefHistory.PainreliefHistory
import domainmodel.medicalrecords.Reports.Reports
import domainmodel.medicalrecords.clinicaldiary.ClinicalDiary
import domainmodel.medicalrecords.initialanalysis.InitialAnalysis

import java.util.UUID

case class MedicalRecordsID(value: UUID)


case class MedicalRecord(
                          medicalRecordID: MedicalRecordsID,
                          initialAnalysis: InitialAnalysis,
                          clinicalDiary: ClinicalDiary,
                          diagnosticServicesRequests: DiagnosticServicesRequests,
                          graphic: Graphic,
                          painreliefHistory: PainreliefHistory,
                          singleSheetTherapy: SingleSheetTherapy,
                          adviceRequest: AdviceRequest,
                          reports: Reports,
                          operatingReports: OperatingReports,
                          nursingDocumentation: NursingDocumentation,
                          anesthesiologyRecord: AnesthesiologyRecord,
                          medicalSurgicalDevices: MedicalSurgicalDevices,
                          dischargeLetter: DischargeLetter) {}


/**
 * Collection of medical records.
 */
object MedicalRecords {

  case class MedicalRecords private(medicalRecords: Set[MedicalRecord] = Set.empty) {

    /**
     * Add new medical records.
     *
     * @param medicalRecord medical record to add.
     * @return collection of medical records.
     */
    def addNewMedicalRecord(medicalRecord: MedicalRecord): MedicalRecords = MedicalRecords(this.medicalRecords + medicalRecord)
  }

  /**
   * Apply method.
   *
   * @return collection of medical records.
   */
  def apply(): MedicalRecords = MedicalRecords()
}