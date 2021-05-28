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

import domainmodel.PathologySeverity.PathologySeverity
import domainmodel.PreviousPathologies.PreviousPathologies
import domainmodel.{DetectionDate, Pathology, PathologyName, PathologySeverityLevels}
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDate

@RunWith(classOf[JUnitRunner])
class GeneralInfoTest extends AnyFreeSpec {

  val generalInfo: GeneralInfo = GeneralInfo(
    BloodGroup(BloodType.AB, Rh.NEGATIVE),
    Weight(75),
    Height(164),
    Some(Allergies().addNewAllergy(
      Allergy(
        AllergyClass.DRUG_ALLERGY,
        AllergyDescription("Good")
      )
    )),
    Some(PreviousPathologies().addNewPathology(
      Pathology(
        PathologyName("Piroplasmosi"),
        DetectionDate(LocalDate.now()),
        PathologySeverity(
          "Grave",
          PathologySeverityLevels.TWO
        )
      )
    )),
    None, None
  )

  val generalInfo1: GeneralInfo = GeneralInfo(
    BloodGroup(BloodType.AB, Rh.NEGATIVE),
    Weight(60),
    Height(167),
    Some(Allergies().addNewAllergy(
      Allergy(
        AllergyClass.DRUG_ALLERGY,
        AllergyDescription("Good")
      )
    )),
    Some(PreviousPathologies().addNewPathology(
      Pathology(
        PathologyName("Piroplasmosi"),
        DetectionDate(LocalDate.now()),
        PathologySeverity(
          "Grave",
          PathologySeverityLevels.TWO
        )
      )
    )),
    Some(PrescriptionHistory().addNewPrescription(
      Prescription(
        PrescriptionDate(),
        PrescriptionInfo("Antiviral")
      )
    )),
    Some(ExamHistory().addNewExam(
      Exam(
        ExamDate(),
        ExamReport("Hearth disease"),
        ExamInfo("Checked the hearth rhythm"))
    ))
  )

  val prescription: Prescription = Prescription(prescriptionDate = PrescriptionDate(), prescriptionInfo = PrescriptionInfo("Antiviral"))
  "A general info should have" - {
    "a blood group" in {
      assert(generalInfo.bloodGroup != null)
    }
    "a blood type in blood group" in {
      assert(generalInfo.bloodGroup.bloodType != null)
    }
    "a rh in blood group" in {
      assert(generalInfo.bloodGroup.rh != null)
    }

    "a weight" in {
      assert(generalInfo.weight != null)
    }
    "a height" in {
      assert(generalInfo.height != null)
    }
  }

  "A general info could have" - {
    "some allergies" in {
      assert(generalInfo.allergies.nonEmpty)
    }
    "some previous pathologies" in {
      assert(generalInfo.previousPathologies.nonEmpty)
    }
    "a prescription history" in {
      assert(generalInfo.prescriptionHistory.isEmpty)
    }
    "a exam history" in {
      assert(generalInfo.examHistory.isEmpty)
    }
  }

  "A general info1 could have" - {
    "some allergies" in {
      assert(generalInfo1.allergies.nonEmpty)
    }
    "some previous pathologies" in {
      assert(generalInfo1.previousPathologies.nonEmpty)
    }
    "a prescription history" in {
      assert(generalInfo1.prescriptionHistory.nonEmpty)
    }
    "a exam history" in {
      assert(generalInfo1.examHistory.nonEmpty)
    }
  }
}
