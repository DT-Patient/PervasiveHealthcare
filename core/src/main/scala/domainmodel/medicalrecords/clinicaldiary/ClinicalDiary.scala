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

import domainmodel.medicalrecords.clinicaldiary.DiagnosticTreatments._
import domainmodel.medicalrecords.clinicaldiary.RehabilitationTreatments._
import domainmodel.medicalrecords.clinicaldiary.TherapeuticTreatments._

/**
 * Clinical diary.
 *
 * @param healthEvolution          evolution of health of patient.
 * @param diagnosticTreatments     diagnostic treatments made.
 * @param therapeuticTreatments    therapeutic treatments made.
 * @param rehabilitationTreatments rehabilitation treatments made.
 */
case class ClinicalDiary(
  healthEvolution: Option[HealthEvolution],
  diagnosticTreatments: Option[DiagnosticTreatments],
  therapeuticTreatments: Option[TherapeuticTreatments],
  rehabilitationTreatments: Option[RehabilitationTreatments]
)
