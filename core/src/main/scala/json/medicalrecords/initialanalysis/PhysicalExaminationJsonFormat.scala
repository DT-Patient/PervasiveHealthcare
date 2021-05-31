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
  jsonFormat2
}
import spray.json.RootJsonFormat

/**
 * Json format for physical examination object.
 */
object PhysicalExaminationJsonFormat {

  /**
   * Implicit for hospitalization motivation object.
   */
  implicit val hospitalizationMotivationJsonFormat
    : RootJsonFormat[HospitalizationMotivation] = jsonFormat1(
    HospitalizationMotivation
  )

  /**
   * Implicit for systems investigation object.
   */
  implicit val systemsInvestigationJsonFormat
    : RootJsonFormat[SystemsInvestigation] = jsonFormat1(SystemsInvestigation)

  /**
   * Implicit for physical examination object.
   */
  implicit val physicalExaminationJsonFormat
    : RootJsonFormat[PhysicalExamination] = jsonFormat2(PhysicalExamination)
}
