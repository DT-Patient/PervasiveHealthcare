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
import domainmodel.generalinfo.Allergies._

@RunWith(classOf[JUnitRunner])
class AllergyTest extends AnyFreeSpec {
  "Allergies can be of eight types" in {
    assert(AllergyClass.maxId == 8)
  }

  val allergy: Allergy =
    Allergy(AllergyClass.CELIAC_DISEASE, AllergyDescription("Celiac disease"))
  "An Allergy" - {
    "should have" - {
      "a type" in {
        assert(AllergyClass.values.contains(allergy.allergyClass))
      }
      "a description" in {
        assert(allergy.description.value.nonEmpty)
      }
    }
  }

  val allergies: Allergies = Allergies()

  "The Allergies of a person" - {
    "should be" - {
      "a set" in {
        assert(allergies.allergies.isInstanceOf[Set[Allergy]])
      }
      "initially empty" in {
        assert(allergies.allergies.isEmpty)
      }
    }
    "can be updated" in {
      assert(allergies.addNewAllergy(allergy).allergies.nonEmpty)
    }
  }
}
