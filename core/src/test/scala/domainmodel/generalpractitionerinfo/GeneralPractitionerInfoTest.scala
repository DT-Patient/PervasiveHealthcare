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

import domainmodel.Familiars.Familiars
import domainmodel.PreviousPathologies.PreviousPathologies
import domainmodel.Remotes.Remotes
import domainmodel._
import domainmodel.generalpractitionerinfo.BookingVisitHistory._
import domainmodel.generalpractitionerinfo.MedicalCertificateHistory._
import domainmodel.generalpractitionerinfo.TherapyHistory._
import domainmodel.generalpractitionerinfo.VisitHistory._
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner
import java.time.LocalDate

import domainmodel.generalinfo.{
  Prescription,
  PrescriptionDate,
  PrescriptionInfo
}
import domainmodel.generalinfo.PrescriptionHistory.PrescriptionHistory

@RunWith(classOf[JUnitRunner])
class GeneralPractitionerInfoTest extends AnyFreeSpec {
  val visit: Visit = Visit(VisitDate())
  val generalVisits: VisitHistory = VisitHistory().addNewVisit(visit)

  val anamnesis: Anamnesis =
    Anamnesis(
      Familiars().addNewFamiliar(
        Familiar(
          "Rossi Mario",
          KinshipDegree.FATHER,
          PreviousPathologies(),
          "3387514876"
        )
      ),
      Remotes().addNewRemote(
        Remote("first anamnesis")
      ),
      Physiologic("self conscious")
    )

  val prescriptions: PrescriptionHistory =
    PrescriptionHistory().addNewPrescription(
      Prescription(PrescriptionDate(), PrescriptionInfo("First prescription"))
    )

  val therapies: TherapyHistory = TherapyHistory().addNewTherapy(
    Therapy(
      TherapyDate(),
      TherapyDescription("Therapy for shoulder surgery"),
      TherapyInitialDate(LocalDate.of(2021, 5, 15)),
      Some(TherapyFinalDate(LocalDate.of(2021, 6, 15)))
    )
  )

  val bookingVisits: BookingVisitHistory =
    BookingVisitHistory().addNewBookingVisit(
      BookingVisit(
        1,
        visit,
        Description("visit for shoulder surgery"),
        LocalDate.now()
      )
    )
  val doctorID: DoctorID = DoctorID("432984632")
  val patientID: PatientID = PatientID("1234")

  val medicalCertificates: MedicalCertificateHistory =
    MedicalCertificateHistory().addNewMedicalCertificate(
      MedicalCertificate(
        MedicalCertificateID("1233"),
        Set.empty[Byte]
      )
    )

  val generalPractitionerInfo: GeneralPractitionerInfo =
    GeneralPractitionerInfo(
      patientID,
      doctorID,
      if (generalVisits.history.nonEmpty) Some(generalVisits) else None,
      if (
        anamnesis.familiars.familiars.nonEmpty && anamnesis.remotes.history.nonEmpty && anamnesis.physiologic != null
      ) Some(anamnesis)
      else None,
      if (bookingVisits.history.nonEmpty) Some(bookingVisits) else None,
      if (prescriptions.history.nonEmpty) Some(prescriptions) else None,
      if (therapies.history.nonEmpty) Some(therapies) else None,
      if (medicalCertificates.history.nonEmpty) Some(medicalCertificates)
      else None
    )

  "A general practitioner info should have" - {
    "a practitioner info" in {
      assert(generalPractitionerInfo.doctorID != null)
    }
    "a visit information" in {
      assert(generalPractitionerInfo.visits != null)
    }
    "an anamnesis info" in {
      assert(generalPractitionerInfo.anamnesis != null)
    }
    "an booking visits info" in {
      assert(generalPractitionerInfo.bookingVisits != null)
    }
    "an prescription info" in {
      assert(generalPractitionerInfo.prescriptions != null)
    }
    "an therapy info" in {
      assert(generalPractitionerInfo.therapies != null)
    }
    "a medical certificates info" in {
      assert(generalPractitionerInfo.medicalCertificateHistory != null)
    }
  }
}
