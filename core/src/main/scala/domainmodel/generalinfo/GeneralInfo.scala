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

import domainmodel.PreviousPathologies._
import domainmodel.generalinfo.Allergies._
import domainmodel.generalinfo.ExamHistory._
import domainmodel.generalinfo.PrescriptionHistory._

/**
 * Class that models the weight of a patient.
 *
 * @param value the weight in kilograms.
 */
case class Weight(value: Double)

/**
 * Class that models the height of a patient.
 *
 * @param value the height in centimeters.
 */
case class Height(value: Int)

/**
 * Class that models the general info of a patient.
 *
 * @param bloodGroup          the blood group.
 * @param weight              the weight.
 * @param height              the height.
 * @param allergies           the allergies.
 * @param previousPathologies the previous pathologies.
 * @param prescriptionHistory the prescriptions history.
 * @param examHistory         the exams history.
 */
case class GeneralInfo(
  bloodGroup: BloodGroup,
  weight: Weight,
  height: Height,
  allergies: Option[Allergies],
  previousPathologies: Option[PreviousPathologies],
  prescriptionHistory: Option[PrescriptionHistory],
  examHistory: Option[ExamHistory]
)
