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

package domainmodel

import domainmodel.PreviousPathologies._
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDate

@RunWith(classOf[JUnitRunner])
class PathologyTest extends AnyFreeSpec {
  "The severity of a pathology can be four levels" in {
    assert(PathologySeverityLevels.maxId == 4)
  }

  "A pathology with severity level" - {
    "1, can cause low disease" in {
      assert(
        PathologySeverity(
          PathologySeverityLevels.ONE
        ).description == "Low disease"
      )
    }
    "2, can change the quality of life" in {
      assert(
        PathologySeverity(
          PathologySeverityLevels.TWO
        ).description == "Changes life quality"
      )
    }
    "3, can cause disability" in {
      assert(
        PathologySeverity(
          PathologySeverityLevels.THREE
        ).description == "Causes disability"
      )
    }
    "4, can threaten life" in {
      assert(
        PathologySeverity(
          PathologySeverityLevels.FOUR
        ).description == "Threatens life"
      )
    }
  }

  val previousPathology: Pathology = Pathology(
    PathologyName("Hearth disease"),
    DetectionDate(),
    PathologySeverity(PathologySeverityLevels.FOUR)
  )
  "A previous pathology should have" - {
    "a name" in {
      assert(previousPathology.pathologyName.value.nonEmpty)
    }
    "a detection date" in {
      assert(previousPathology.detectionDate != null)
    }
    "a severity" in {
      assert(previousPathology.pathologySeverity != null)
    }
  }

  "A detection date should not be after the current date" in {
    previousPathology.detectionDate.value.isAfter(LocalDate.now())
  }

  val previousPathologies: PreviousPathologies = PreviousPathologies()

  "Previous pathologies" - {
    "should be" - {
      "a set" in {
        assert(previousPathologies.pathologies.isInstanceOf[Set[Pathology]])
      }
      "initially empty" in {
        assert(previousPathologies.pathologies.isEmpty)
      }
    }
    "can be added" in {
      assert(
        previousPathologies
          .addNewPathology(previousPathology)
          .pathologies
          .nonEmpty
      )
    }
  }

}
