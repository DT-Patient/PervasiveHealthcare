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

import domainmodel.medicalrecords.PharmacologicalTherapies._
import domainmodel.utility.Description
import java.time.{LocalDate, LocalDateTime}

import domainmodel.DoctorID

/**
 * Admission reason.
 *
 * @param value text of admission reason.
 */
case class AdmissionReason(value: String)

/**
 * Clinical problems encountered.
 *
 * @param value text of clinical problems encountered.
 */
case class ClinicalProblemsEncountered(value: String)

/**
 * Significant findings.
 *
 * @param value text of significant findings.
 */
case class SignificantFindings(value: String)

/**
 * Discharge diagnosis.
 *
 * @param value text of discharge diagnosis.
 */
case class DischargeDiagnosis(value: String)

/**
 * Follow-Up instructions.
 *
 * @param value text of follow-up instructions.
 */
case class FollowUpInstructions(value: String)

/**
 * Post hospital care information.
 *
 * @param value text of post hospital care information.
 */
case class PostHospitalCareInformation(value: String)

/**
 * SDO compilation.
 *
 * @param value text of SDO compilation.
 */
case class SdoCompilation(value: String)

/**
 * Pharmacological Therapy.
 *
 * @param doctorID      doctor that administrated this.
 * @param datetime    date and time of administration.
 * @param description description of pharmacological therapy.
 * @param initialDate initial date to administration.
 * @param finalDate   final date to administration.
 */
case class PharmacologicalTherapy(
  doctorID: DoctorID,
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description,
  initialDate: LocalDate,
  finalDate: LocalDate
)

/**
 * Collection of pharmacological therapy.
 */
object PharmacologicalTherapies {

  case class PharmacologicalTherapies private (
    pharmacologicalTherapies: Set[PharmacologicalTherapy] = Set.empty
  ) {

    /**
     * Method to add pharmacological therapy.
     *
     * @param pharmacologicalTherapy pharmacological therapy to add.
     * @return collection of pharmacological therapies.
     */
    def addNewPharmacologicalTherapy(
      pharmacologicalTherapy: PharmacologicalTherapy
    ): PharmacologicalTherapies =
      PharmacologicalTherapies(
        this.pharmacologicalTherapies + pharmacologicalTherapy
      )
  }

  /**
   * Apply method.
   *
   * @return collection of pharmacological therapies.
   */
  def apply(): PharmacologicalTherapies = PharmacologicalTherapies()
}

/**
 * Discharge letter.
 *
 * @param admissionReason             admission reason.
 * @param clinicalProblemsEncountered clinical problems encountered.
 * @param significantFindings         significant findings.
 * @param pharmacologicalTherapies    pharmacological therapies.
 * @param dischargeDiagnosis          discharge diagnosis.
 * @param followUpInstructions        follow-up instructions.
 * @param sdoCompilation              SDO compilation.
 */
case class DischargeLetter(
  admissionReason: AdmissionReason,
  clinicalProblemsEncountered: ClinicalProblemsEncountered,
  significantFindings: SignificantFindings,
  pharmacologicalTherapies: PharmacologicalTherapies,
  dischargeDiagnosis: DischargeDiagnosis,
  followUpInstructions: FollowUpInstructions,
  sdoCompilation: SdoCompilation
)
