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

package json.generalinfo

import domainmodel.generalinfo.ExamHistory.ExamHistory
import domainmodel.generalinfo.{Exam, ExamDate, ExamInfo, ExamReport}
import json.LocalDateJsonFormat.DateFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  immSetFormat,
  jsonFormat1,
  jsonFormat3
}
import spray.json.RootJsonFormat

/**
 * Json format for exam object.
 */
object ExamJsonFormat {

  /**
   * Implicit for exam date object.
   */
  implicit val examDateJsonFormat: RootJsonFormat[ExamDate] = jsonFormat1(
    ExamDate
  )

  /**
   * Implicit for exam report object.
   */
  implicit val examReportJsonFormat: RootJsonFormat[ExamReport] = jsonFormat1(
    ExamReport
  )

  /**
   * Implicit for exam info object.
   */
  implicit val examInfoJsonFormat: RootJsonFormat[ExamInfo] = jsonFormat1(
    ExamInfo
  )

  /**
   * Implicit for exam object.
   */
  implicit val examJsonFormat: RootJsonFormat[Exam] = jsonFormat3(Exam)

  /**
   * Implicit for exam history object.
   */
  implicit val examHistoryJsonFormat: RootJsonFormat[ExamHistory] = jsonFormat1(
    ExamHistory
  )
}
