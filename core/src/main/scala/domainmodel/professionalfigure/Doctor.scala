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

package domainmodel.professionalfigure

import domainmodel.DoctorID
import domainmodel.professionalfigure.Specialization.Specialization

trait Doctor {
  val doctorID: DoctorID
  val name: String
  val surname: String
  val phoneNumber: String
  val email: String
  val medicalDegreeGrade: String
}

object Specialization extends Enumeration {
  type Specialization = Value

  val GENERAL_SURGERY, THORACIC_SURGERY, NEUROLOGICAL_SURGERY,
    ORTHOPAEDIC_SURGERY, PEDIATRIC_SURGERY, PLASTIC_SURGERY, VASCULAR_SURGERY =
    Value
}

/**
 * Surgeon.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param medicalDegreeGrade , medical degree grade.
 */
case class Surgeon(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val medicalDegreeGrade: String,
  specialization: Specialization
) extends Doctor

/**
 * Anesthetist.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param medicalDegreeGrade , medical degree grade.
 */
case class Anesthetist(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val medicalDegreeGrade: String
) extends Doctor

/**
 * General Practitioner.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param medicalDegreeGrade , medical degree grade.
 */
case class GeneralPractitioner(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val medicalDegreeGrade: String
) extends Doctor

/**
 * Cardiologist.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param medicalDegreeGrade , medical degree grade.
 */
case class Cardiologist(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val medicalDegreeGrade: String
) extends Doctor
