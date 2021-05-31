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

package domainmodel.medicalrecords.initialanalysis

/**
 * Psychological.
 *
 * @param value , value of psychological.
 */
case class Psychological(value: String)

/**
 * Nutritional.
 *
 * @param value , value of nutritional.
 */
case class Nutritional(value: String)

/**
 * Educational.
 *
 * @param value , value of educational.
 */
case class Educational(value: String)

/**
 * Social.
 *
 * @param value , value of social.
 */
case class Social(value: String)

/**
 * State Evaluation.
 *
 * @param psychological , psychological.
 * @param nutritional   , nutritional.
 * @param educational   , educational.
 * @param social        , social.
 */
case class StateEvaluation(
  psychological: Psychological,
  nutritional: Nutritional,
  educational: Educational,
  social: Social
)
