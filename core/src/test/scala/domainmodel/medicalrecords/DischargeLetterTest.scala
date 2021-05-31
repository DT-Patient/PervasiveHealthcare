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

import domainmodel.medicalrecords.PharmacologicalTherapies._
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner
import java.time.{LocalDate, LocalDateTime}

import domainmodel.DoctorID

@RunWith(classOf[JUnitRunner])
class DischargeLetterTest extends AnyFreeSpec {

  val pharmacologicalTherapies: PharmacologicalTherapies =
    PharmacologicalTherapies()

  "The pharmacological therapies " - {
    "should be" - {
      "a set" in {
        assert(
          pharmacologicalTherapies.pharmacologicalTherapies
            .isInstanceOf[Set[PharmacologicalTherapy]]
        )
      }
      "initially empty" in {
        assert(pharmacologicalTherapies.pharmacologicalTherapies.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        pharmacologicalTherapies
          .addNewPharmacologicalTherapy(
            PharmacologicalTherapy(
              DoctorID("HBDYA"),
              LocalDateTime.now(),
              Description("2 compress"),
              LocalDate.of(2021, 6, 3),
              LocalDate.of(2021, 6, 6)
            )
          )
          .pharmacologicalTherapies
          .nonEmpty
      )
    }
  }

  val dischargeLetter: DischargeLetter = DischargeLetter(
    AdmissionReason("Broken leg"),
    ClinicalProblemsEncountered("Nothing"),
    SignificantFindings("Nothing"),
    pharmacologicalTherapies,
    DischargeDiagnosis("Patient complete recovered"),
    FollowUpInstructions("two tablets of brufen per day"),
    SdoCompilation("all right")
  )
  "A discharge letter should have" - {
    "an admission reason" in {
      assert(dischargeLetter.admissionReason.value.nonEmpty)
    }
    "an clinical problems encountered" in {
      assert(dischargeLetter.clinicalProblemsEncountered.value.nonEmpty)
    }
    "an significant findings" in {
      assert(dischargeLetter.significantFindings.value.nonEmpty)
    }
    "a SDO compilation" in {
      assert(dischargeLetter.sdoCompilation.value.nonEmpty)
    }
    "an discharge diagnosis" in {
      assert(dischargeLetter.dischargeDiagnosis.value.nonEmpty)
    }
    "a follow-up instructions" in {
      assert(dischargeLetter.followUpInstructions.value.nonEmpty)
    }
  }

}
