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

package domainmodel.utility

import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestUtility extends AnyFreeSpec {

  val information: Info = Info("Two injection at day")

  "An information should have" - {
    "a information" in {
      assert(information.value != null)
    }
    "is a text value" in {
      assert(information.value.isInstanceOf[String])
    }
  }

  val description: Description = Description("Two injection at day")

  "A description  should have" - {
    "a information" in {
      assert(description.value != null)
    }
    "is a text value" in {
      assert(description.value.isInstanceOf[String])
    }
  }

  val notes: Note = Note("Two injection at day")

  "A note should have" - {
    "a information" in {
      assert(notes.value != null)
    }
    "is a text value" in {
      assert(notes.value.isInstanceOf[String])
    }
  }
}
