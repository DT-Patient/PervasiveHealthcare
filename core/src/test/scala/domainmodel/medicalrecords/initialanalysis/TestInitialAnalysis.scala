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

import domainmodel.Familiars.Familiars
import domainmodel.Remotes.Remotes
import domainmodel._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestInitialAnalysis extends AnyFreeSpec {
  val remote: Remote = Remote("first anamnesis")
  val remotes: Remotes = Remotes().addNewRemote(remote)

  val familiar: Familiar = Familiar(
    "Rossi Mario",
    KinshipDegree.FATHER,
    PreviousPathologies(),
    "3387514876"
  )
  val familiars: Familiars = Familiars().addNewFamiliar(familiar)

  val anamnesis: Anamnesis =
    Anamnesis(familiars, remotes, Physiologic("self conscious"))

  val initialAnalysis: InitialAnalysis = InitialAnalysis(
    Some(anamnesis),
    PhysicalExamination(
      HospitalizationMotivation("Hearth attack"),
      SystemsInvestigation("Electrocardiogram")
    ),
    StateEvaluation(
      Psychological("All OK"),
      Nutritional("All OK"),
      Educational("All OK"),
      Social("All OK")
    )
  )
  "An initial analysis should have" - {
    /*"an anamnesis" in {
      assert(initialAnalysis.anamensis != null)
      assert(initialAnalysis.anamensis.isInstanceOf[Anamnesis])
    }*/
    "a physical exaimnation" in {
      assert(initialAnalysis.physicalExamination != null)
      assert(
        initialAnalysis.physicalExamination.isInstanceOf[PhysicalExamination]
      )
    }
    "a state evaluation" in {
      assert(initialAnalysis.stateEvaluation != null)
      assert(initialAnalysis.stateEvaluation.isInstanceOf[StateEvaluation])
    }
  }
}
