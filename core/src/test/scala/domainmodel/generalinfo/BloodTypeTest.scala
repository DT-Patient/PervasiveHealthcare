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

import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest._
import freespec._

@RunWith(classOf[JUnitRunner])
class BloodTypeTest extends AnyFreeSpec {
  "BloodType can be of four types" in {
    assert(BloodType.maxId == 4)
  }

  "Rh can be of two types" in {
    assert(Rh.maxId == 2)
  }

  val bloodGroup: BloodGroup = BloodGroup(BloodType.ZERO, Rh.POSITIVE)
  "Blood group should have" - {
    "a type" in {
      assert(BloodType.values.contains(bloodGroup.bloodType))
    }
    "a Rh" in {
      assert(Rh.values.contains(bloodGroup.rh))
    }
  }
}
