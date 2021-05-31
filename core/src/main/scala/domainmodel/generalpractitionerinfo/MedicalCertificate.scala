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

package domainmodel.generalpractitionerinfo

import domainmodel.ID

case class MedicalCertificateID(override val value: String) extends ID

/**
 * This class represents the set of the patient's medical certificates.
 *
 * @param medicalCertificateID , id of medical certificate
 * @param medicalCertificate   , pdf of medical certificate.
 */
case class MedicalCertificate(
  medicalCertificateID: MedicalCertificateID,
  medicalCertificate: Set[Byte]
)

/**
 * The set of medical certificates of the patient.
 */
object MedicalCertificateHistory {

  /**
   * Class that models the history of a patient's medical certificates.
   *
   * @param history the set of medical certificates.
   */
  case class MedicalCertificateHistory private (
    history: Set[MedicalCertificate] = Set.empty
  ) {

    /**
     * Add a new medical certificate to the history.
     *
     * @param medicalCertificate the medical certificate to add.
     * @return the updated history.
     */
    def addNewMedicalCertificate(
      medicalCertificate: MedicalCertificate
    ): MedicalCertificateHistory =
      MedicalCertificateHistory(this.history + medicalCertificate)
  }

  /**
   * Factory method per MedicalCertificateHistory.
   *
   * @return an empty set of medical certificates.
   */
  def apply(): MedicalCertificateHistory = MedicalCertificateHistory()

}
