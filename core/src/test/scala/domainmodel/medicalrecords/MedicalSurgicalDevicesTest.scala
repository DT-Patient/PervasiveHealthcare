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

import domainmodel.medicalrecords.MedicalSurgicalDevices._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MedicalSurgicalDevicesTest extends AnyFreeSpec {

  val medicalSurgicalDevice: MedicalSurgicalDevice =
    MedicalSurgicalDevice("scalpel", "newly")
  "A medical surgical device should have" - {
    "a name" in {
      assert(medicalSurgicalDevice.name.nonEmpty)
    }
    "a label" in {
      assert(medicalSurgicalDevice.label.nonEmpty)
    }
  }

  val medicalSurgicalDevices: MedicalSurgicalDevices = MedicalSurgicalDevices()

  "medical surgical devices" - {
    "should be" - {
      "a set" in {
        assert(
          medicalSurgicalDevices.medicalSurgicalDevices
            .isInstanceOf[Set[MedicalSurgicalDevice]]
        )
      }
      "initially empty" in {
        assert(medicalSurgicalDevices.medicalSurgicalDevices.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        medicalSurgicalDevices
          .addNewMedicalSurgicalDevice(medicalSurgicalDevice)
          .medicalSurgicalDevices
          .nonEmpty
      )
    }
  }
}
