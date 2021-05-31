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

package json.medicalrecords.initialanalysis

import domainmodel.medicalrecords.initialanalysis._
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat4
}
import spray.json.RootJsonFormat

/**
 * Json format for state evaluation object.
 */
object StateEvaluationJsonFormat {

  /**
   * Implicit for psychological object.
   */
  implicit val psychologicalJsonFormat: RootJsonFormat[Psychological] =
    jsonFormat1(Psychological)

  /**
   * Implicit for nutritional object.
   */
  implicit val nutritionalJsonFormat: RootJsonFormat[Nutritional] = jsonFormat1(
    Nutritional
  )

  /**
   * Implicit for educational object.
   */
  implicit val educationalJsonFormat: RootJsonFormat[Educational] = jsonFormat1(
    Educational
  )

  /**
   * Implicit for social object.
   */
  implicit val socialJsonFormat: RootJsonFormat[Social] = jsonFormat1(Social)

  /**
   * Implicit for state evaluation object.
   */
  implicit val stateEvaluationJsonFormat: RootJsonFormat[StateEvaluation] =
    jsonFormat4(StateEvaluation)

}
