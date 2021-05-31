/*
 *
 *  * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *  *
 *  *                              Licensed under the Apache License, Version 2.0 (the "License");
 *  *                              you may not use this file except in compliance with the License.
 *  *                              You may obtain a copy of the License at
 *  *
 *  *                                  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *                              Unless required by applicable law or agreed to in writing, software
 *  *                              distributed under the License is distributed on an "AS IS" BASIS,
 *  *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *                              See the License for the specific language governing permissions and
 *  *                              limitations under the License.
 *
 */

package domainmodel.professionalfigure

import domainmodel.DoctorID
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RescuerTest extends AnyFreeSpec {

  val rescuer: Rescuer = Rescuer(
    DoctorID("123"),
    "Luca",
    "Manfredi",
    "3214569876",
    "surgeon@gmail.com",
    "102"
  )

  "A rescuer should have" - {
    "a doctor ID" in {
      assert(rescuer.doctorID != null)
    }
    "a name and surname" in {
      assert(rescuer.name != null && rescuer.surname != null)
    }
    "a phone number" in {
      assert(rescuer.phoneNumber != null)
    }
    "a mail" in {
      assert(rescuer.email != null)
    }
    "a medical degree grade" in {
      assert(rescuer.medicalDegreeGrade != null)
    }
  }
}
