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

package json.medicalrecords

import domainmodel.medicalrecords.{
  AnesthesiologyRecord,
  AnestheticCard,
  OperationEvaluation,
  PostOperationEvaluation
}
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.professionalfigure.ProfessionalFigureJsonFormat.AnesthetistJsonFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.jsonFormat2
import spray.json.RootJsonFormat

/**
 * Json format for anesthesiology record object.
 */
object AnesthesiologyRecordJsonFormat {

  /**
   * Implicit for anesthesiology record object.
   */
  implicit val anesthesiologyRecordJsonFormat
    : RootJsonFormat[AnesthesiologyRecord] = jsonFormat2(AnesthesiologyRecord)

  /**
   * Implicit for operation evaluation object.
   */
  implicit val operationEvaluationJsonFormat
    : RootJsonFormat[OperationEvaluation] = jsonFormat2(OperationEvaluation)

  /**
   * Implicit for post operation object.
   */
  implicit val postOperationEvaluationJsonFormat
    : RootJsonFormat[PostOperationEvaluation] = jsonFormat2(
    PostOperationEvaluation
  )

  /**
   * Implicit for anesthetic card object.
   */
  implicit val anestheticCardJsonFormat: RootJsonFormat[AnestheticCard] =
    jsonFormat2(AnestheticCard)

}
