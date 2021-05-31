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

import java.time.LocalDate
import domainmodel.generalpractitionerinfo.TherapyHistory._
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TherapyTest extends AnyFreeSpec {

  val therapy: Therapy = Therapy(
    TherapyDate(),
    TherapyDescription("Therapy for shoulder surgery"),
    TherapyInitialDate(LocalDate.of(2021, 5, 15)),
    Some(TherapyFinalDate(LocalDate.of(2021, 6, 15)))
  )
  "A therapy should have" - {
    "a date" in {
      assert(therapy.therapyDate != null)
    }
    "a description" in {
      assert(therapy.therapyDescription != null)
    }
    "an initial date" in {
      assert(therapy.therapyInitialDate != null)
    }
    "a final date" in {
      assert(therapy.therapyFinalDate != null)
    }
  }

  "A therapy date should not be after the current date" in {
    therapy.therapyDate.therapyDate.isAfter(LocalDate.now())
  }

  val therapies: TherapyHistory = TherapyHistory()
  "A therapies" - {
    "should be initially empty" in {
      assert(therapies.history.isEmpty)
    }

    "can added" in {
      assert(therapies.addNewTherapy(therapy).history.nonEmpty)
    }
  }

}
