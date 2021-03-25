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

import domainmodel.medicalrecords.{AnesthesiologyRecord, AnestheticCard, OperationEvaluation, PostOperationEvaluation}
import spray.json.DefaultJsonProtocol.jsonFormat2
import spray.json.RootJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat.AnesthetistJsonFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import json.LocalDateTimeJsonFormat.DateTimeFormat

object AnesthesiologyRecordJsonFormat {

  implicit val anesthesiologyRecordJsonFormat: RootJsonFormat[AnesthesiologyRecord] = jsonFormat2(AnesthesiologyRecord)
  implicit val operationEvaluationJsonFormat: RootJsonFormat[OperationEvaluation] = jsonFormat2(OperationEvaluation)
  implicit val postOperationEvaluationJsonFormat: RootJsonFormat[PostOperationEvaluation] = jsonFormat2(PostOperationEvaluation)
  implicit val anestheticCardJsonFormat: RootJsonFormat[AnestheticCard] = jsonFormat2(AnestheticCard)

}
