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

package json.generalpractitionerinfo

import domainmodel.generalinfo.PrescriptionHistory.PrescriptionHistory
import domainmodel.generalpractitionerinfo.BookingVisitHistory.BookingVisitHistory
import domainmodel.generalpractitionerinfo.TherapyHistory.TherapyHistory
import domainmodel.generalpractitionerinfo.VisitHistory.VisitHistory
import domainmodel.generalpractitionerinfo._
import domainmodel.{Anamnesis, DoctorID}
import json.AnamnesisJsonFormat.{
  familiarsJsonFormat,
  physiologicJsonFormat,
  remotesJsonFormat
}
import json.IDJsonFormat.patientIDJsonFormat
import json.RequestJsonFormats.{
  StringJsonFormat,
  immSetFormat,
  jsonFormat3,
  jsonFormat8,
  optionFormat
}
import json.generalinfo.PrescriptionJsonFormat.prescriptionJsonFormat
import json.generalpractitionerinfo.BookingVisitsJsonFormat.bookingVisitJsonFormat
import json.generalpractitionerinfo.MedicalCertificatesJsonFormat.medicalCertificateHistoryJsonFormat
import json.generalpractitionerinfo.TherapyJsonFormat.therapyJsonFormat
import json.generalpractitionerinfo.VisitJsonFormat.visitJsonFormat
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.RootJsonFormat

/**
 * Json format for general practitioner object.
 */
object GeneralPractitionerInfoJsonFormat {

  /**
   * Implicit for doctor id of general practitioner object.
   */
  implicit val generalPractitionerInfoDoctorJsonFormat
    : RootJsonFormat[DoctorID] = jsonFormat1(DoctorID)

  /**
   * Implicit for visits object.
   */
  implicit val generalPractitionerInfoVisitsJsonFormat
    : RootJsonFormat[VisitHistory] = jsonFormat1(VisitHistory)

  /**
   * Implicit for anamnesis object.
   */
  implicit val generalPractitionerInfoAnamnesisJsonFormat
    : RootJsonFormat[Anamnesis] = jsonFormat3(Anamnesis)

  /**
   * Implicit for booking visits object.
   */
  implicit val generalPractitionerInfoBookingVisitsJsonFormat
    : RootJsonFormat[BookingVisitHistory] = jsonFormat1(BookingVisitHistory)

  /**
   * Implicit for prescriptions object.
   */
  implicit val generalPractitionerInfoPrescriptionsJsonFormat
    : RootJsonFormat[PrescriptionHistory] = jsonFormat1(PrescriptionHistory)

  /**
   * Implicit for therapies object.
   */
  implicit val generalPractitionerInfoTherapiesJsonFormat
    : RootJsonFormat[TherapyHistory] = jsonFormat1(TherapyHistory)

  /**
   * Implicit for practitioner info object.
   */
  implicit val generalPractitionerInfoJsonFormat
    : RootJsonFormat[GeneralPractitionerInfo] = jsonFormat8(
    GeneralPractitionerInfo
  )

}
