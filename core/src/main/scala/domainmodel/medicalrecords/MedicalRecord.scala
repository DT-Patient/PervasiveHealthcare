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

import domainmodel.medicalrecords.DiagnosticServicesRequests._
import domainmodel.medicalrecords.MedicalSurgicalDevices._
import domainmodel.medicalrecords.PainreliefHistory._
import domainmodel.medicalrecords.Reports._
import domainmodel.medicalrecords.SingleSheetTherapies._
import domainmodel.medicalrecords.clinicaldiary.ClinicalDiary
import domainmodel.medicalrecords.initialanalysis.InitialAnalysis
import domainmodel.{DoctorID, PatientID}

/**
 * Medical record ID
 *
 * @param value , value of ID.
 */
case class MedicalRecordsID(value: String)

/**
 * Medical Record.
 *
 * @param doctorID                   , doctor ID.
 * @param patientID                  , patient ID.
 * @param medicalRecordID            ,medical record ID.
 * @param isClosed                   ,is closed.
 * @param initialAnalysis            ,initial analysis.
 * @param clinicalDiary              ,clinical diary.
 * @param diagnosticServicesRequests ,diagnostic services requests.
 * @param graphic                    ,graphic.
 * @param painReliefHistory          ,pain relief history.
 * @param singleSheetTherapyHistory  ,single sheet therapy history.
 * @param adviceRequest              ,advice request.
 * @param reports                    ,reports.
 * @param operatingReports           ,operating reports.
 * @param nursingDocumentation       ,nursing documentation.
 * @param anesthesiologyRecord       ,anesthesiology record.
 * @param medicalSurgicalDevices     ,medical surgical devices.
 * @param dischargeLetter            ,discharge letter.
 */
case class MedicalRecord(
  doctorID: DoctorID,
  patientID: PatientID,
  medicalRecordID: MedicalRecordsID,
  isClosed: Boolean,
  initialAnalysis: Option[InitialAnalysis] = None,
  clinicalDiary: Option[ClinicalDiary] = None,
  diagnosticServicesRequests: Option[DiagnosticServicesRequests] = None,
  graphic: Option[Graphic] = None,
  painReliefHistory: Option[PainreliefHistory] = None,
  singleSheetTherapyHistory: Option[SingleSheetTherapies] = None,
  adviceRequest: Option[AdviceRequest] = None,
  reports: Option[Reports] = None,
  operatingReports: Option[OperatingReports] = None,
  nursingDocumentation: Option[NursingDocumentation] = None,
  anesthesiologyRecord: Option[AnesthesiologyRecord] = None,
  medicalSurgicalDevices: Option[MedicalSurgicalDevices] = None,
  dischargeLetter: Option[DischargeLetter] = None
)

/**
 * Collection of medical records.
 */
object MedicalRecordHistory {

  case class MedicalRecordHistory private (
    history: Set[MedicalRecord] = Set.empty
  ) {

    /**
     * Add new medical records.
     *
     * @param medicalRecord medical record to add.
     * @return collection of medical records.
     */
    def addNewMedicalRecord(
      medicalRecord: MedicalRecord
    ): MedicalRecordHistory = {
      if (
        this.history exists (x =>
          x.medicalRecordID.value == medicalRecord.medicalRecordID.value
        )
      )
        MedicalRecordHistory(
          this.history - (this.history
            .find(x => x.medicalRecordID equals medicalRecord.medicalRecordID)
            .get)
            + medicalRecord
        )
      else
        MedicalRecordHistory(this.history + medicalRecord)
    }
  }

  /**
   * Apply method.
   *
   * @return collection of medical records.
   */
  def apply(): MedicalRecordHistory = MedicalRecordHistory()
}
