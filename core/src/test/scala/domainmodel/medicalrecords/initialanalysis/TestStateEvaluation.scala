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

package domainmodel.medicalrecords.initialanalysis

import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestStateEvaluation extends AnyFreeSpec {

  val stateEvaluation: StateEvaluation = StateEvaluation(
    Psychological("All OK"),
    Nutritional("All OK"),
    Educational("All OK"),
    Social("All OK")
  )
  "A state evaluation should have" - {
    "an psycological state" in {
      assert(stateEvaluation.psychological != null)
      assert(stateEvaluation.psychological.equals(Psychological("All OK")))

    }
    "an nutritional state" in {
      assert(stateEvaluation.nutritional != null)
      assert(stateEvaluation.nutritional.equals(Nutritional("All OK")))
    }
    "an educational state" in {
      assert(stateEvaluation.educational != null)
      assert(stateEvaluation.educational.equals(Educational("All OK")))
    }
    "an social state" in {
      assert(stateEvaluation.social != null)
      assert(stateEvaluation.social.equals(Social("All OK")))
    }
  }
}
