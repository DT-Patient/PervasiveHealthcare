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

import domainmodel.generalinfo.GeneralInfo
import domainmodel.utility.Description

import java.time.LocalDateTime

/**
 * Nursing Documentation.
 *
 * @param registration                , registration.
 * @param needsIdentification         , needs identification.
 * @param nursingInterventionPlanning , nursing intervention planning.
 * @param careDiary                   , care diary.
 * @param interventionEvaluation      , intervention evaluation.
 */
case class NursingDocumentation(
  registration: Registration,
  needsIdentification: Option[NeedsIdentification],
  nursingInterventionPlanning: Option[NursingInterventionPlanning],
  careDiary: Option[CareDiary],
  interventionEvaluation: Option[InterventionEvaluation]
)

/**
 * Intervention Evaluation.
 *
 * @param value , value of intervention evaluation.
 */
case class InterventionEvaluation(value: String)

/**
 * Care Diary.
 *
 * @param value , value of care diary.
 */
case class CareDiary(value: String)

/**
 * Nursing Intervention Planning.
 *
 * @param datetime    , date and time.
 * @param description , description.
 */
case class NursingInterventionPlanning(
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description
)

/**
 * Needs Identification.
 *
 * @param datetime    , date and time.
 * @param description , description.
 */
case class NeedsIdentification(
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description
)

/**
 * Registration.
 *
 * @param personalData , personal data.
 */
case class Registration(personalData: GeneralInfo)
