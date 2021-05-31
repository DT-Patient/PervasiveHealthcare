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

package domainmodel.medicalrecords.clinicaldiary

import domainmodel.DoctorID
import domainmodel.utility.Description

import java.time.LocalDate

/**
 * General treatment.
 *
 * @param date        date of treatment.
 * @param description description of treatment.
 * @param doctorID    doctorID that has made treatment.
 */
case class Treatment(
  date: LocalDate,
  description: Description,
  doctorID: DoctorID
)

/**
 * Diagnostic Treatments
 *
 * @param treatment treatment executed.
 */
case class DiagnosticTreatment(treatment: Treatment)

/**
 * Collection of diagnostic treatments.
 */
object DiagnosticTreatments {

  case class DiagnosticTreatments private (
    diagnosticTreatments: Set[DiagnosticTreatment] = Set.empty
  ) {

    /**
     * Method to add new diagnostic treatment at the collection.
     *
     * @param diagnosticTreatment diagnostic treatment to add.
     * @return collection of diagnostic treatment.
     */
    def addNewDiagnosticTreatment(
      diagnosticTreatment: DiagnosticTreatment
    ): DiagnosticTreatments =
      DiagnosticTreatments(this.diagnosticTreatments + diagnosticTreatment)
  }

  /**
   * Apply method.
   *
   * @return collection of diagnostic treatments.
   */
  def apply(): DiagnosticTreatments = DiagnosticTreatments()
}

/**
 * Therapeutic Treatments
 *
 * @param treatment treatment executed.
 */
case class TherapeuticTreatment(treatment: Treatment)

/**
 * Collection of therapeutic treatments.
 */
object TherapeuticTreatments {

  case class TherapeuticTreatments private (
    therapeuticTreatments: Set[TherapeuticTreatment] = Set.empty
  ) {

    /**
     * Method to add new therapeutic treatment at the collection.
     *
     * @param therapeuticTreatment therapeutic treatment to add.
     * @return collection of therapeutic treatment.
     */
    def addNewTherapeuticTreatment(
      therapeuticTreatment: TherapeuticTreatment
    ): TherapeuticTreatments =
      TherapeuticTreatments(this.therapeuticTreatments + therapeuticTreatment)
  }

  /**
   * Apply method.
   *
   * @return collection of therapeutic treatments.
   */
  def apply(): TherapeuticTreatments = TherapeuticTreatments()
}

/**
 * Rehabilitation Treatments
 *
 * @param treatment treatment executed.
 */
case class RehabilitationTreatment(treatment: Treatment)

/**
 * Collection of rehabilitation treatments.
 */
object RehabilitationTreatments {

  case class RehabilitationTreatments private (
    rehabilitationTreatments: Set[RehabilitationTreatment] = Set.empty
  ) {

    /**
     * Method to add new rehabilitation treatment at the collection.
     *
     * @param rehabilitationTreatment rehabilitation treatment to add.
     * @return collection of rehabilitation treatment.
     */
    def addNewRehabilitationTreatment(
      rehabilitationTreatment: RehabilitationTreatment
    ): RehabilitationTreatments =
      RehabilitationTreatments(
        this.rehabilitationTreatments + rehabilitationTreatment
      )
  }

  /**
   * Apply method.
   *
   * @return collection of rehabilitation treatment.
   */
  def apply(): RehabilitationTreatments = RehabilitationTreatments()
}
