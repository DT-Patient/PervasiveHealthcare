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

package domainmodel

import domainmodel.Patient._
import domainmodel.generalinfo.{BloodGroup, BloodType, GeneralInfo, Height, Rh, Weight}
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

import java.time.LocalDate

@RunWith(classOf[JUnitRunner])
class PatientTest extends AnyFreeSpec {
  val patient: Patient = Patient.Patient(
    PatientID("1234"),
    CF("aaa"),
    "Luca",
    "Verdi",
    LocalDate.of(1990, 11, 22),
    "Cesena",
    Gender.MALE,
    "054488888",
    Some("33355548"),
    "Via Cerchia 20",
    "...",
    "Cesena",
    "FC",
    None,
    None,
    None,
    None
  )

  "A patient should have" - {
    "a name" in {
      assert(patient.name.nonEmpty)
    }
    "a surname" in {
      assert(patient.surname.nonEmpty)
    }
    "a fiscal code" in {
      assert(patient.cf.value.nonEmpty)
    }
    "a birthday date" in {
      assert(patient.birthDate != null)
    }
    "a gender type" in {
      assert(patient.gender != null)
    }
    "an address" in {
      assert(patient.address.nonEmpty)
    }
    "a city" in {
      assert(patient.residenceCity.nonEmpty)
    }
    "a province" in {
      assert(patient.province.nonEmpty)
    }
  }

  Patient.updateGeneralInfo(patient, GeneralInfo(
    BloodGroup(BloodType.AB, Rh.NEGATIVE),
    Weight(68.5),
    Height(172),
    None, None,
    None, None
  ))


  "An updated patient should have" - {
    "a name" in {
      assert(patient.name.nonEmpty)
    }
    "a surname" in {
      assert(patient.surname.nonEmpty)
    }
    "a fiscal code" in {
      assert(patient.cf.value.nonEmpty)
    }
    "a birthday date" in {
      assert(patient.birthDate != null)
    }
    "a gender type" in {
      assert(patient.gender != null)
    }
    "an address" in {
      assert(patient.address.nonEmpty)
    }
    "a city" in {
      assert(patient.residenceCity.nonEmpty)
    }
    "a province" in {
      assert(patient.province.nonEmpty)
    }
    "a general info" in {
      assert(patient.generalInfo.isEmpty)
    }
  }



}