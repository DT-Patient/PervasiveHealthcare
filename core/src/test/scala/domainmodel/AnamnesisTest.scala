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

import domainmodel.Familiars._
import domainmodel.Remotes._
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AnamnesisTest extends AnyFreeSpec {
  val remote: Remote = Remote("first anamnesis")
  val remotes: Remotes = Remotes()
  remotes.addNewRemote(remote)

  val familiar: Familiar = Familiar(
    "Rossi Mario",
    KinshipDegree.FATHER,
    PreviousPathologies(),
    "3387514876"
  )
  val familiars: Familiars = Familiars()
  familiars.addNewFamiliar(familiar)

  val anamnesis: Anamnesis =
    Anamnesis(familiars, remotes, Physiologic("self conscious"))
  "An anamnesis should have" - {
    "a familiar information" in {
      assert(anamnesis.familiars != null)
    }
    "a remote information" in {
      assert(anamnesis.remotes != null)
    }
    "a physiologic information" in {
      assert(anamnesis.physiologic != null)
    }
  }

  "A remotes" - {
    "should be initially empty" in {
      assert(remotes.history.isEmpty)
    }
    "can added" in {
      assert(remotes.addNewRemote(remote).history.nonEmpty)
    }
  }

  "A familiars" - {
    "should be initially empty" in {
      assert(familiars.familiars.isEmpty)
    }

    "can added" in {
      assert(familiars.addNewFamiliar(familiar).familiars.nonEmpty)
    }
  }

}
