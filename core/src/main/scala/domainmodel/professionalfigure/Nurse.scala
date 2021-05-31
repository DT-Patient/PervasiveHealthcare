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

abstract class Nurse() {
  val doctorID: DoctorID
  val name: String
  val surname: String
  val phoneNumber: String
  val email: String
  val nursingDegreeGrade: String
}

/**
 * Instrumentalist.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param nursingDegreeGrade , nursing degree grade.
 */
case class Instrumentalist(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val nursingDegreeGrade: String
) extends Nurse

/**
 * Ward Nurse.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param nursingDegreeGrade , nursing degree grade.
 */
case class WardNurse(
  override val doctorID: DoctorID,
  override val name: String,
  override val surname: String,
  override val phoneNumber: String,
  override val email: String,
  override val nursingDegreeGrade: String
) extends Nurse
