/*
 * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *
 *                              Licensed under the Apache License, Version 2.0 (the "License");
 *                              you may not use this file except in compliance with the License.
 *                              You may obtain a copy of the License at
 *
 *                                  http://www.apache.org/licenses/LICENSE-2.0
 *
 *                              Unless required by applicable law or agreed to in writing, software
 *                              distributed under the License is distributed on an "AS IS" BASIS,
 *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *                              See the License for the specific language governing permissions and
 *                              limitations under the License.
 */

package domainmodel.medicalrecords.clinicaldiary

import domainmodel.medicalrecords.initialanalysis._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestStateEvaluation extends AnyFreeSpec {

  val stateEvaluation: StateEvaluation = StateEvaluation(
    Psychological("Nothing to highlight"),
    Nutritional("Nothing to highlight"),
    Educational("Nothing to highlight"),
    Social("Nothing to highlight")
  )
  "A state evolution should have" - {
    "an psychological information" in {
      assert(stateEvaluation.psychological.value != null)
      assert(stateEvaluation.psychological.value.equals("Nothing to highlight"))
    }
    "an nutritional information" in {
      assert(stateEvaluation.nutritional.value != null)
      assert(stateEvaluation.nutritional.value.equals("Nothing to highlight"))
    }
    "an educational information" in {
      assert(stateEvaluation.educational.value != null)
      assert(stateEvaluation.educational.value.equals("Nothing to highlight"))
    }
    "an social information" in {
      assert(stateEvaluation.social.value != null)
      assert(stateEvaluation.social.value.equals("Nothing to highlight"))
    }
  }
}
