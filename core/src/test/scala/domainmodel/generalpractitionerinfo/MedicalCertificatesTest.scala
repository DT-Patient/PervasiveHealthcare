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

package domainmodel.generalpractitionerinfo

import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MedicalCertificatesTest extends AnyFreeSpec {
  val bytes: Set[Byte] = Set()

  val medicalCertificate: MedicalCertificate =
    MedicalCertificate(MedicalCertificateID("1"), bytes)
  "A medical certificate should have" - {
    "an id" in {
      assert(
        medicalCertificate.medicalCertificateID == MedicalCertificateID("1")
      )
    }
    "a set of bytes" in {
      assert(medicalCertificate.medicalCertificate != null)
    }
  }
}
