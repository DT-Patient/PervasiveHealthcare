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

package domainmodel.generalpractitionerinfo

import domainmodel.generalpractitionerinfo.VisitHistory.VisitHistory
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class VisitTest extends AnyFreeSpec {
  val visit: Visit = Visit(VisitDate())
  "A visit" - {
    "should have" - {
      "a date" in {
        assert(visit.visitDate != null)
      }
    }
  }

  val visits: VisitHistory = VisitHistory()
  "A visits" - {
    "should be initially empty" in {
      assert(visits.history.isEmpty)
    }

    "can added" in {
      assert(visits.addNewVisit(visit).history.nonEmpty)
    }
  }
}