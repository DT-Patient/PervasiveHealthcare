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

import java.time.LocalDate

/**
 * Class that models the date of an exam.
 *
 * @param value the exam data.
 */
case class ExamDate(value: LocalDate = LocalDate.now())

/**
 * Class that models the report of an exam.
 *
 * @param value a brief report about the exam.
 */
case class ExamReport(value: String)

/**
 * Class that models the information of an exam.
 *
 * @param value a brief description about the exam.
 */
case class ExamInfo(value: String)

/**
 * Class that models a medical exam.
 *
 * @param examDate   the date of the exam.
 * @param examReport the report of the exam.
 * @param examInfo   the description of the exam.
 */
case class Exam(examDate: ExamDate, examReport: ExamReport, examInfo: ExamInfo)

/**
 * The set of exams done by a patient.
 */
object ExamHistory {

  /**
   * Class that models the history of a patient's exams.
   *
   * @param history the set of exams.
   */
  case class ExamHistory private (history: Set[Exam] = Set.empty) {

    /**
     * Add a new exams to the history.
     *
     * @param exam the exam to add.
     * @return the updated history.
     */
    def addNewExam(exam: Exam): ExamHistory = ExamHistory(this.history + exam)
  }

  /**
   * Factory method for ExamHistory.
   *
   * @return an empty set of exams.
   */
  def apply(): ExamHistory = ExamHistory()
}
