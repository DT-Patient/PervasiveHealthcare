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

package domainmodel.generalinfo

import domainmodel.generalinfo.PrescriptionHistory._
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrescriptionTest extends AnyFreeSpec {

  val prescription: Prescription = Prescription(
    prescriptionDate = PrescriptionDate(),
    prescriptionInfo = PrescriptionInfo("Antiviral")
  )

  "A prescription should have" - {
    "a date" in {
      assert(prescription.prescriptionDate != null)
    }
    "a brief information description" in {
      assert(prescription.prescriptionInfo.value.nonEmpty)
    }
  }

  val prescriptionHistory: PrescriptionHistory = PrescriptionHistory()

  "Prescription history" - {
    "should be" - {
      "a set" in {
        assert(prescriptionHistory.history.isInstanceOf[Set[Prescription]])
      }
      "initially empty" in {
        assert(prescriptionHistory.history.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        prescriptionHistory.addNewPrescription(prescription).history.nonEmpty
      )
    }
  }
}
