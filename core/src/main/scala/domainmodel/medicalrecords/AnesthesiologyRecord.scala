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

import domainmodel.professionalfigure.Anesthetist
import domainmodel.utility.Description

import java.time.LocalDateTime

/**
 * Anesthesiology Record.
 *
 * @param anesthetist         , anesthetist.
 * @param operationEvaluation , operation evaluation.
 */
case class AnesthesiologyRecord(
  anesthetist: Anesthetist,
  operationEvaluation: OperationEvaluation
)

/**
 * Operation Evaluation.
 *
 * @param anestheticCard          , anesthetic card.
 * @param postOperationEvaluation ,postOperation evaluation.
 */
case class OperationEvaluation(
  anestheticCard: AnestheticCard,
  postOperationEvaluation: PostOperationEvaluation
)

/**
 * Post Operation Evaluation.
 *
 * @param datetime    , date and time.
 * @param description , description.
 */
case class PostOperationEvaluation(
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description
)

/**
 * Anesthetic Card.
 *
 * @param datetime    , date and time.
 * @param description , description.
 */
case class AnestheticCard(
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description
)
