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

package domainmodel.medicalrecords

import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NursingDocumentationTest extends AnyFreeSpec {
  val nursingDocumentation: NursingDocumentation =
    NursingDocumentation(
      Registration(null, null),
      NeedsIdentification(description = Description("Yes")),
      NursingInterventionPlanning(description = Description("Group working")),
      CareDiary("Ok"),
      InterventionEvaluation("All right")
    )
  "A nursing documentation should have" - {
    "a needs identification" in {
      assert(nursingDocumentation.needsIdentification.datetime != null)
      assert(nursingDocumentation.needsIdentification.description.equals(Description("Yes")))
    }
    "a nursing intervention plannig" in {
      assert(nursingDocumentation.nursingInterventionPlanning.datetime != null)
    }
    "a care diary" in {
      assert(nursingDocumentation.careDiary != null)
      assert(nursingDocumentation.careDiary.value.equals("Ok"))
    }
    "a intervention evaluation" in {
      assert(nursingDocumentation.interventionEvaluation != null)
      assert(nursingDocumentation.interventionEvaluation.value.equals("All right"))
    }
  }
}