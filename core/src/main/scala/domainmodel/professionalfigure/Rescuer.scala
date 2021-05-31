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

/**
 * Rescuer.
 *
 * @param doctorID           , doctorID.
 * @param name               , name.
 * @param surname            , surname.
 * @param phoneNumber        , phone number.
 * @param email              , mail.
 * @param medicalDegreeGrade , medical degree grade.
 */
case class Rescuer(
  doctorID: DoctorID,
  name: String,
  surname: String,
  phoneNumber: String,
  email: String,
  medicalDegreeGrade: String
)
