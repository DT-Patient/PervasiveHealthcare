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

package domainmodel.medicalrecords

import domainmodel.medicalrecords.Reports._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDateTime

@RunWith(classOf[JUnitRunner])
class ReportsTest extends AnyFreeSpec {

  val report: Report = Report(
    Activity(
      Diagnostics("diagnosis"),
      Consulting("medical consulting"),
      TherapeuticDelivery("Nothing"),
      Rehabilitation("Nothing"),
      Assistance("Nothing")
    ),
    LocalDateTime.now(),
    TreatmentType("Massage")
  )
  "A report should have" - {
    "an activity" in {
      assert(report.activity != null)
      assert(report.activity.diagnostics.value.nonEmpty)
      assert(report.activity.consulting.value.nonEmpty)
      assert(report.activity.therapeuticDelivery.value.nonEmpty)
      assert(report.activity.rehabilitation.value.nonEmpty)
      assert(report.activity.assistance.value.nonEmpty)
    }
    "a date time" in {
      assert(report.datetime != null)
    }
    "a treatment type" in {
      assert(report.treatmentType.value.nonEmpty)
    }
  }

  val reports: Reports = Reports()

  "Prescription history" - {
    "should be" - {
      "a set" in {
        assert(reports.reports.isInstanceOf[Set[Report]])
      }
      "initially empty" in {
        assert(reports.reports.isEmpty)
      }
    }
    "can be updated" in {
      assert(reports.addNewReport(report).reports.nonEmpty)
    }
  }
}
