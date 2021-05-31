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
package server.models

import akka.actor.typed.ActorRef
import cqrs.writemodel.Role.Role
import domainmodel.ChestPainType.ChestPainType
import domainmodel.Gender.Gender
import domainmodel.KinshipDegree.KinshipDegree
import domainmodel.Patient.Patient
import domainmodel.RestingElectrocardiographic.RestingElectrocardiographic
import domainmodel.SlopeST.SlopeST
import domainmodel.Thal.Thal
import domainmodel._
import domainmodel.generalinfo.AllergyClass.AllergyClass
import domainmodel.generalinfo.BloodType.BloodType
import domainmodel.generalinfo.GeneralInfo
import domainmodel.generalinfo.Rh.Rh
import domainmodel.generalpractitionerinfo.{GeneralPractitionerInfo, Visit}
import domainmodel.medicalrecords.clinicaldiary.ClinicalDiary
import domainmodel.medicalrecords.{
  DrugsAdministered,
  MedicalRecord,
  MedicalRecordsID
}
import domainmodel.professionalfigure.Specialization.Specialization
import domainmodel.professionalfigure._

/**
 * This object contains all query and all commands that can be executed.
 */
object Protocol {

  /**
   * Basilar actions.
   */
  sealed trait CQRSAction

  sealed trait Command extends CQRSAction

  sealed trait Event

  sealed trait Confirmation

  sealed trait Query extends CQRSAction

  final case class LoginAccepted(role: Role, description: String, token: String)
    extends Confirmation

  final case class Accepted(description: String) extends Confirmation

  final case class Rejected(reason: String) extends Confirmation

  //Authentication protocol
  final case class Login(user: User, replyTo: ActorRef[Confirmation])
    extends Command

  final case class Logout(token: String, replyTo: ActorRef[Confirmation])
    extends Command

  //Administrator protocol is also authentication for the signup
  final case class InsertSurgeon(
    surgeon: Surgeon,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateSurgeon(
    id: String,
    surgeon: Surgeon,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetSurgeons(replyTo: ActorRef[Set[Surgeon]]) extends Query

  final case class GetSurgeon(id: String, replyTo: ActorRef[Option[Surgeon]])
    extends Query

  final case class RemoveSurgeon(
    surgeon: Surgeon,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class SurgeonAdded(surgeon: Surgeon) extends Event

  final case class SurgeonUpdated(id: DoctorID, surgeon: Surgeon) extends Event

  final case class SurgeonRemoved(surgeon: Surgeon) extends Event

  final case class InsertAnesthetist(
    anesthetist: Anesthetist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateAnesthetist(
    id: String,
    anesthetist: Anesthetist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetAnesthetists(replyTo: ActorRef[Set[Anesthetist]])
    extends Query

  final case class GetAnesthetist(
    id: String,
    replyTo: ActorRef[Option[Anesthetist]]
  ) extends Query

  final case class InsertGeneralPractitioner(
    generalPractitioner: GeneralPractitioner,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateGeneralPractitioner(
    id: String,
    generalPractitioner: GeneralPractitioner,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetGeneralPractitioners(
    replyTo: ActorRef[Set[GeneralPractitioner]]
  ) extends Query

  final case class GetGeneralPractitionerInfo(
    id: DoctorID,
    replyTo: ActorRef[Set[GeneralPractitionerInfo]]
  ) extends Query

  final case class GetGeneralPractitioner(
    id: String,
    replyTo: ActorRef[Option[GeneralPractitioner]]
  ) extends Query

  final case class InsertInstrumentalist(
    instrumentalist: Instrumentalist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateInstrumentalist(
    id: String,
    instrumentalist: Instrumentalist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetInstrumentalists(replyTo: ActorRef[Set[Instrumentalist]])
    extends Query

  final case class GetInstrumentalist(
    id: String,
    replyTo: ActorRef[Option[Instrumentalist]]
  ) extends Query

  final case class InsertRescuer(
    rescuer: Rescuer,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateRescuer(
    id: String,
    rescuer: Rescuer,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetRescuers(replyTo: ActorRef[Set[Rescuer]]) extends Query

  final case class GetRescuer(id: String, replyTo: ActorRef[Option[Rescuer]])
    extends Query

  final case class InsertWardNurse(
    wardNurse: WardNurse,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateWardNurse(
    id: String,
    wardNurse: WardNurse,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetWardNurses(replyTo: ActorRef[Set[WardNurse]])
    extends Query

  final case class GetWardNurse(
    id: String,
    replyTo: ActorRef[Option[WardNurse]]
  ) extends Query

  final case class InsertCardiologist(
    cardiologist: Cardiologist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateCardiologist(
    id: String,
    cardiologist: Cardiologist,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetCardiologist(
    id: String,
    replyTo: ActorRef[Option[Patient]]
  ) extends Command

  final case class InsertPatient(
    patient: Patient,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdatePatient(
    id: String,
    patient: Patient,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetPatient(id: String, replyTo: ActorRef[Option[Patient]])
    extends Query

  final case class GetCardiologyVisits(
    id: DoctorID,
    replyTo: ActorRef[Set[CardiologyVisit]]
  ) extends Query

  final case class GetCardiologyPredictions(
    id: DoctorID,
    replyTo: ActorRef[Seq[CardiologyPrediction]]
  ) extends Query

  final case class UpdateCardiologyPredictions(
    id: DoctorID,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  //Doctor protocol
  final case class InsertMedicalRecord(
    medicalRecord: MedicalRecord,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateMedicalRecord(
    medicalRecordID: MedicalRecordsID,
    medicalRecord: MedicalRecord,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class GetAllMedicalRecordsForDoctor(
    doctorID: DoctorID,
    replyTo: ActorRef[Set[MedicalRecord]]
  ) extends Query

  final case class UpdateClinicalDiary(
    medicalRecordID: MedicalRecordsID,
    clinicalDiary: ClinicalDiary,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class InsertVisit(
    patientId: String,
    visit: Visit,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateVisit(
    patientId: String,
    visit: Visit,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class InsertDrugAdministered(
    medicalRecordID: MedicalRecordsID,
    drugAdministered: DrugsAdministered,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateDrugAdministered(
    medicalRecordID: MedicalRecordsID,
    drugsAdministered: DrugsAdministered,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class InsertGeneralPractitionerInfo(
    generalPractitionerInfo: GeneralPractitionerInfo,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateGeneralPractitionerInfo(
    patientID: PatientID,
    generalPractitionerInfo: GeneralPractitionerInfo,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class InsertGeneralInfo(
    generalInfo: GeneralInfo,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class UpdateGeneralInfo(
    patientID: PatientID,
    generalInfo: GeneralInfo,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  final case class InsertCardiologyVisit(
    cardiologyVisit: CardiologyVisit,
    replyTo: ActorRef[Confirmation]
  ) extends Command

  //Enumerations

  final case class GetRoles(replyTo: ActorRef[Set[Role]]) extends Query

  final case class GetGenders(replyTo: ActorRef[Set[Gender]]) extends Query

  final case class GetAllergies(replyTo: ActorRef[Set[AllergyClass]])
    extends Query

  final case class GetBloodTypes(replyTo: ActorRef[Set[BloodType]])
    extends Query

  final case class GetRh(replyTo: ActorRef[Set[Rh]]) extends Query

  final case class GetSpecializations(replyTo: ActorRef[Set[Specialization]])
    extends Query

  final case class GetKinshipDegrees(replyTo: ActorRef[Set[KinshipDegree]])
    extends Query

  final case class GetChestPainTypes(replyTo: ActorRef[Set[ChestPainType]])
    extends Query

  final case class GetRestingECG(
    replyTo: ActorRef[Set[RestingElectrocardiographic]]
  ) extends Query

  final case class GetSlopeST(replyTo: ActorRef[Set[SlopeST]]) extends Query

  final case class GetThals(replyTo: ActorRef[Set[Thal]]) extends Query

}
