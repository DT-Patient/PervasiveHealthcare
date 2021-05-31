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

import domainmodel.medicalrecords._
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.generalinfo.GeneralInfoJsonFormat.generalInfoJsonFormat
import json.utility.InfoDescriptionJsonFormat.descriptionJsonFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat2,
  jsonFormat5,
  optionFormat
}
import spray.json.RootJsonFormat

/**
 * Json format for nursing documentation object.
 */
object NursingDocumentationJsonFormat {

  /**
   * Implicit for nursing documentation object.
   */
  implicit val nursingDocumentationJsonFormat
    : RootJsonFormat[NursingDocumentation] = jsonFormat5(NursingDocumentation)

  /**
   * Implicit for intervention evaluation object.
   */
  implicit val interventionEvaluationJsonFormat
    : RootJsonFormat[InterventionEvaluation] = jsonFormat1(
    InterventionEvaluation
  )

  /**
   * Implicit for care diary object.
   */
  implicit val careDiaryJsonFormat: RootJsonFormat[CareDiary] = jsonFormat1(
    CareDiary
  )

  /**
   * Implicit for nursing intervention planning object.
   */
  implicit val nursingInterventionPlanningJsonFormat
    : RootJsonFormat[NursingInterventionPlanning] = jsonFormat2(
    NursingInterventionPlanning
  )

  /**
   * Implicit for needs identification object.
   */
  implicit val needsIdentificationJsonFormat
    : RootJsonFormat[NeedsIdentification] = jsonFormat2(NeedsIdentification)

  /**
   * Implicit for registration object.
   */
  implicit val registrationJsonFormat: RootJsonFormat[Registration] =
    jsonFormat1(Registration)
}
