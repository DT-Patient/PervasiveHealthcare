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

import domainmodel.utility.Info
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDateTime

@RunWith(classOf[JUnitRunner])
class GraphicTest extends AnyFreeSpec {

  val vitalSign: VitalSign = VitalSign(
    Info("Pressure: 180"),
    LocalDateTime.now()
  )
  "A vital sign should have" - {
    "a date" in {
      assert(vitalSign.datetime != null)
    }
    "a brief information " in {
      assert(vitalSign.info.value.nonEmpty)
    }
  }

  val graphic: Graphic = Graphic(VitalSigns())

  "Graphic" - {
    "should be" - {
      "a set" in {
        assert(graphic.vitalSigns.vitalSigns.isInstanceOf[Set[VitalSign]])
      }
      "initially empty" in {
        assert(graphic.vitalSigns.vitalSigns.isEmpty)
      }
    }
    "can be updated" in {
      assert(graphic.vitalSigns.addNewVitalSign(vitalSign).vitalSigns.nonEmpty)
    }
  }
}
