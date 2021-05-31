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

package domainmodel.medicalrecords.clinicaldiary

import domainmodel.utility.Info
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDateTime

@RunWith(classOf[JUnitRunner])
class TestHealthEvolution extends AnyFreeSpec {

  val health: HealthEvolution =
    HealthEvolution(Info("The patient defecated"), LocalDateTime.now())
  "An health evolution should have" - {
    "an information field" in {
      assert(
        health.info != null && health.info.equals(Info("The patient defecated"))
      )
    }
    "a date of registration" in {
      assert(health.dateTime != null)
      assert(health.dateTime.isInstanceOf[LocalDateTime])
    }
  }
}
