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

import java.time.LocalDateTime

@RunWith(classOf[JUnitRunner])
class PainreliefTest extends AnyFreeSpec {

  val painrelief: Painrelief =
    Painrelief(LocalDateTime.now(), Description("One dose at day"))
  "An painrelief should have" - {
    "a date time" in {
      assert(painrelief.datetime != null)
      assert(painrelief.datetime.isInstanceOf[LocalDateTime])
    }
    "a description" in {
      assert(painrelief.description != null)
      assert(painrelief.description.equals(Description("One dose at day")))
    }
  }
}
