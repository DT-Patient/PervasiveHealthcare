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

package domainmodel.generalinfo

import java.time.LocalDate

/**
 * Class that models the date of a prescription.
 *
 * @param value the date.
 */
case class PrescriptionDate(value: LocalDate = LocalDate.now())

/**
 * Class that models the information about the prescription.
 *
 * @param value the information.
 */
case class PrescriptionInfo(value: String)

/**
 * Class that models a prescription.
 *
 * @param prescriptionDate the date of the prescription.
 * @param prescriptionInfo the information of the prescription.
 */
case class Prescription(
  prescriptionDate: PrescriptionDate,
  prescriptionInfo: PrescriptionInfo
)

/**
 * The set of prescriptions of the patient.
 */
object PrescriptionHistory {

  /**
   * Class that models the history of a patient's prescriptions.
   *
   * @param history the set of prescriptions.
   */
  case class PrescriptionHistory private (
    history: Set[Prescription] = Set.empty
  ) {

    /**
     * Add a new prescription to the history.
     *
     * @param prescription the prescription to add.
     * @return the updated history.
     */
    def addNewPrescription(prescription: Prescription): PrescriptionHistory =
      PrescriptionHistory(this.history + prescription)
  }

  /**
   * Factory method per PrescriptionHistory.
   *
   * @return an empty set od prescriptions.
   */
  def apply(): PrescriptionHistory = PrescriptionHistory()

}
