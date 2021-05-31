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
class TestPhysicalExamination extends AnyFreeSpec {

  val physicalExamination: PhysicalExamination = PhysicalExamination(
    HospitalizationMotivation("Hearth attack"),
    SystemsInvestigation("Electrocardiogram")
  )

  "A physical examination should have" - {
    "an hospitalization motivation" in {
      assert(physicalExamination.hospitalizationMotivation.value != null)
      assert(
        physicalExamination.hospitalizationMotivation.value.equals(
          "Hearth attack"
        )
      )
    }
    "a system of investigation" in {
      assert(physicalExamination.systemsInvestigation.value != null)
      assert(
        physicalExamination.systemsInvestigation
          .isInstanceOf[SystemsInvestigation]
      )
    }
  }

}
