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

import domainmodel.medicalrecords.clinicaldiary.DiagnosticTreatments._
import domainmodel.medicalrecords.clinicaldiary.RehabilitationTreatments._
import domainmodel.medicalrecords.clinicaldiary.TherapeuticTreatments._
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner
import java.time.LocalDate

import domainmodel.DoctorID

@RunWith(classOf[JUnitRunner])
class TestTreatments extends AnyFreeSpec {

  val diagnostic: DiagnosticTreatment = DiagnosticTreatment(
    Treatment(LocalDate.now(), Description("X-Ray"), DoctorID("KUGDBIUB!$"))
  )

  "A diagnostic treatment should have" - {
    "a date" in {
      assert(diagnostic.treatment.date != null)
    }
    "a brief information description" in {
      assert(diagnostic.treatment.description.value.nonEmpty)
    }
    "a doctor that register treatment" in {
      assert(diagnostic.treatment.doctorID.value.nonEmpty)
    }
  }

  val therapeutic: TherapeuticTreatment = TherapeuticTreatment(
    Treatment(
      LocalDate.now(),
      Description("Physiotherapy"),
      DoctorID("YVDII!$")
    )
  )

  "A therapeutic treatment should have" - {
    "a date" in {
      assert(therapeutic.treatment.date != null)
    }
    "a brief information description" in {
      assert(therapeutic.treatment.description.value.nonEmpty)
    }
    "a doctor that register treatment" in {
      assert(therapeutic.treatment.doctorID.value.nonEmpty)
    }
  }

  val rehabilitation: RehabilitationTreatment = RehabilitationTreatment(
    Treatment(LocalDate.now(), Description("X-Ray"), DoctorID("KUGDBIUB!$"))
  )

  "A rehabilitation treatment should have" - {
    "a date" in {
      assert(rehabilitation.treatment.date != null)
    }
    "a brief information description" in {
      assert(rehabilitation.treatment.description.value.nonEmpty)
    }
    "a doctor that register treatment" in {
      assert(rehabilitation.treatment.doctorID.value.nonEmpty)
    }
  }

  val diagnostics: DiagnosticTreatments = DiagnosticTreatments()
  "Diagnostic treatments" - {
    "should be" - {
      "a set" in {
        assert(
          diagnostics.diagnosticTreatments
            .isInstanceOf[Set[DiagnosticTreatment]]
        )
      }
      "initially empty" in {
        assert(diagnostics.diagnosticTreatments.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        diagnostics
          .addNewDiagnosticTreatment(diagnostic)
          .diagnosticTreatments
          .nonEmpty
      )
    }
  }

  val therapeutics: TherapeuticTreatments = TherapeuticTreatments()
  "Therapeutic treatments" - {
    "should be" - {
      "a set" in {
        assert(
          therapeutics.therapeuticTreatments
            .isInstanceOf[Set[TherapeuticTreatment]]
        )
      }
      "initially empty" in {
        assert(therapeutics.therapeuticTreatments.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        therapeutics
          .addNewTherapeuticTreatment(therapeutic)
          .therapeuticTreatments
          .nonEmpty
      )
    }
  }

  val rehabilitations: RehabilitationTreatments = RehabilitationTreatments()
  "Rehabilitation treatments" - {
    "should be" - {
      "a set" in {
        assert(
          rehabilitations.rehabilitationTreatments
            .isInstanceOf[Set[RehabilitationTreatment]]
        )
      }
      "initially empty" in {
        assert(rehabilitations.rehabilitationTreatments.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        rehabilitations
          .addNewRehabilitationTreatment(rehabilitation)
          .rehabilitationTreatments
          .nonEmpty
      )
    }
  }
}
