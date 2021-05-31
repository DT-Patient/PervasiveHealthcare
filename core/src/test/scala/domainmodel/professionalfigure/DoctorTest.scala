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
import domainmodel.professionalfigure.Specialization.GENERAL_SURGERY
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DoctorTest extends AnyFreeSpec {

  val surgeon: Surgeon = Surgeon(
    DoctorID("123"),
    "Luca",
    "Manfredi",
    "3214569876",
    "surgeon@gmail.com",
    "102",
    GENERAL_SURGERY
  )

  "A surgeon should have" - {
    "a doctor ID" in {
      assert(surgeon.doctorID != null)
    }
    "a name and surname" in {
      assert(surgeon.name != null && surgeon.surname != null)
    }
    "a phone number" in {
      assert(surgeon.phoneNumber != null)
    }
    "a mail" in {
      assert(surgeon.email != null)
    }
    "a medical degree grade" in {
      assert(surgeon.medicalDegreeGrade != null)
    }
    "a specialization" in {
      assert(surgeon.specialization != null)
    }
  }

  val anesthetist: Anesthetist = Anesthetist(
    DoctorID("123"),
    "Luca",
    "Manfredi",
    "3214569876",
    "luca@gmail.com",
    "102"
  )

  "A anesthetist should have" - {
    "a doctor ID" in {
      assert(anesthetist.doctorID != null)
    }
    "a name and surname" in {
      assert(anesthetist.name != null && anesthetist.surname != null)
    }
    "a phone number" in {
      assert(anesthetist.phoneNumber != null)
    }
    "a mail" in {
      assert(anesthetist.email != null)
    }
    "a medical degree grade" in {
      assert(anesthetist.medicalDegreeGrade != null)
    }
  }

  val generalPractitioner: GeneralPractitioner = GeneralPractitioner(
    DoctorID("123"),
    "Luca",
    "Manfredi",
    "3214569876",
    "surgeon@gmail.com",
    "102"
  )

  "A general practitioner should have" - {
    "a doctor ID" in {
      assert(generalPractitioner.doctorID != null)
    }
    "a name and surname" in {
      assert(
        generalPractitioner.name != null && generalPractitioner.surname != null
      )
    }
    "a phone number" in {
      assert(generalPractitioner.phoneNumber != null)
    }
    "a mail" in {
      assert(generalPractitioner.email != null)
    }
    "a medical degree grade" in {
      assert(generalPractitioner.medicalDegreeGrade != null)
    }
  }

  val cardiologist: Cardiologist = Cardiologist(
    DoctorID("123"),
    "Luca",
    "Manfredi",
    "3214569876",
    "surgeon@gmail.com",
    "102"
  )

  "A cardiologist should have" - {
    "a doctor ID" in {
      assert(cardiologist.doctorID != null)
    }
    "a name and surname" in {
      assert(cardiologist.name != null && cardiologist.surname != null)
    }
    "a phone number" in {
      assert(cardiologist.phoneNumber != null)
    }
    "a mail" in {
      assert(cardiologist.email != null)
    }
    "a medical degree grade" in {
      assert(cardiologist.medicalDegreeGrade != null)
    }
  }
}
