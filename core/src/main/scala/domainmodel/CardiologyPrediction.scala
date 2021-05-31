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

package domainmodel

trait CardiologyDiseasePrediction

/**
 * Cardiology diseases presence.
 */
case class CardiologyDiseasesPresence() extends CardiologyDiseasePrediction

/**
 * Cardiology diseases absence.
 */
case class CardiologyDiseasesAbsence() extends CardiologyDiseasePrediction

/**
 * Cardiology predictions.
 *
 * @param patientID       , ID of the patient.
 * @param doctorID        , ID of the doctor.
 * @param cardiologyVisit , cardiology visit.
 * @param seen            , prediction seen.
 */
case class CardiologyPrediction(
  patientID: PatientID,
  doctorID: DoctorID,
  cardiologyVisit: CardiologyVisit,
  seen: Boolean = false
)
