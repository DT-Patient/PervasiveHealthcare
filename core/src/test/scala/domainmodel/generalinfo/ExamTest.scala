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

import domainmodel.generalinfo.ExamHistory._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ExamTest extends AnyFreeSpec {

  val exam: Exam = Exam(
    ExamDate(),
    ExamReport("Hearth disease"),
    ExamInfo("Checked the hearth rhythm")
  )

  "An exam should have" - {
    "a date" in {
      assert(exam.examDate != null)
    }
    "a report of the exam" in {
      assert(exam.examReport.value.nonEmpty)
    }
    "a brief information description" in {
      assert(exam.examInfo.value.nonEmpty)
    }
  }

  val examHistory: ExamHistory = ExamHistory()

  "Exams history" - {
    "should be" - {
      "a set" in {
        assert(examHistory.history.isInstanceOf[Set[Exam]])
      }
      "initially empty" in {
        assert(examHistory.history.isEmpty)
      }
    }
    "can be updated" in {
      assert(examHistory.addNewExam(exam).history.nonEmpty)
    }
  }
}
