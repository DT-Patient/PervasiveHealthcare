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

package cqrs.readmodel.eventsourcing

import domainmodel.Patient.Patient
import domainmodel._
import domainmodel.generalinfo.GeneralInfo
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.medicalrecords.MedicalRecord
import domainmodel.professionalfigure._

import java.time.LocalDateTime

/**
 * Event's type enumeration.
 */
object EventType extends Enumeration {

  type EventType = Value

  val INSERT_SURGEON, UPDATE_SURGEON, INSERT_ANESTHETIST, UPDATE_ANESTHETIST,
    INSERT_GENERAL_PRACTITIONER, UPDATE_GENERAL_PRACTITIONER,
    INSERT_INSTRUMENTALIST, UPDATE_INSTRUMENTALIST, INSERT_WARD_NURSE,
    UPDATE_WARD_NURSE, INSERT_RESCUER, UPDATE_RESCUER, INSERT_CARDIOLOGIST,
    UPDATE_CARDIOLOGIST, INSERT_PATIENT_INFO, UPDATE_PATIENT_INFO,
    INSERT_MEDICAL_RECORD, UPDATE_MEDICAL_RECORD, INSERT_GENERAL_INFO,
    UPDATE_GENERAL_INFO, INSERT_GENERAL_PRACTITIONER_INFO,
    UPDATE_GENERAL_PRACTITIONER_INFO, INSERT_CARDIOLOGY_VISIT,
    UPDATE_CARDIOLOGY_VISIT, INSERT_CARDIOLOGY_PREDICTION,
    UPDATE_CARDIOLOGY_PREDICTION = Value
}

/**
 * Abstract class of general event.
 *
 * @param created , date.
 * @param userID, id of user.
 */
abstract class Event(created: LocalDateTime, userID: ID) {
  final val time = created
}

/**
 * Event of insert of surgeon.
 *
 * @param id        , id of the surgeon.
 * @param s         , surgeon to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertSurgeonEvent(
  id: DoctorID,
  s: Surgeon,
  eventID: Int = EventType.INSERT_SURGEON.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of surgeon.
 *
 * @param id        , id of the surgeon.
 * @param s         , surgeon to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateSurgeonEvent(
  id: DoctorID,
  s: Surgeon,
  eventID: Int = EventType.UPDATE_SURGEON.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of anesthetist.
 *
 * @param id        , id of the anesthetist.
 * @param a         , anesthetist to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertAnesthetistEvent(
  id: DoctorID,
  a: Anesthetist,
  eventID: Int = EventType.INSERT_ANESTHETIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of anesthetist.
 *
 * @param id        , id of the anesthetist.
 * @param a         , anesthetist to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateAnesthetistEvent(
  id: DoctorID,
  a: Anesthetist,
  eventID: Int = EventType.INSERT_ANESTHETIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of general practitioner.
 *
 * @param id        , id of the general practitioner.
 * @param g         , general practitioner to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertGeneralPractitionerEvent(
  id: DoctorID,
  g: GeneralPractitioner,
  eventID: Int = EventType.INSERT_GENERAL_PRACTITIONER.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of general practitioner.
 *
 * @param id        , id of the general practitioner.
 * @param g         , general practitioner to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateGeneralPractitionerEvent(
  id: DoctorID,
  g: GeneralPractitioner,
  eventID: Int = EventType.UPDATE_GENERAL_PRACTITIONER.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of instrumentalist.
 *
 * @param id        , id of the instrumentalist.
 * @param i         , instrumentalist to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertInstrumentalistEvent(
  id: DoctorID,
  i: Instrumentalist,
  eventID: Int = EventType.INSERT_INSTRUMENTALIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of instrumentalist.
 *
 * @param id        , id of the instrumentalist.
 * @param i         , instrumentalist to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateInstrumentalistEvent(
  id: DoctorID,
  i: Instrumentalist,
  eventID: Int = EventType.UPDATE_INSTRUMENTALIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of ward nurse.
 *
 * @param id        , id of the ward nurse.
 * @param w         , ward nurse to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertWardNurseEvent(
  id: DoctorID,
  w: WardNurse,
  eventID: Int = EventType.INSERT_WARD_NURSE.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of ward nurse.
 *
 * @param id        , id of the ward nurse.
 * @param w         , ward nurse to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateWardNurseEvent(
  id: DoctorID,
  w: WardNurse,
  eventID: Int = EventType.UPDATE_WARD_NURSE.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of rescuer.
 *
 * @param id        , id of the rescuer.
 * @param r         , rescuer to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertRescuerEvent(
  id: DoctorID,
  r: Rescuer,
  eventID: Int = EventType.INSERT_RESCUER.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of rescuer.
 *
 * @param id        , id of the rescuer.
 * @param r         , rescuer to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateRescuerEvent(
  id: DoctorID,
  r: Rescuer,
  eventID: Int = EventType.UPDATE_RESCUER.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of cardiologist.
 *
 * @param id        , id of the rescuer.
 * @param c         , cardiologist to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertCardiologistEvent(
  id: DoctorID,
  c: Cardiologist,
  eventID: Int = EventType.INSERT_CARDIOLOGIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of cardiologist.
 *
 * @param id        , id of the rescuer.
 * @param c         , cardiologist to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateCardiologistEvent(
  id: DoctorID,
  c: Cardiologist,
  eventID: Int = EventType.UPDATE_CARDIOLOGIST.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of patient info.
 *
 * @param id        , id of the patient.
 * @param p         , patient info to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertPatientInfoEvent(
  id: PatientID,
  p: Patient,
  eventID: Int = EventType.INSERT_PATIENT_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of patient info.
 *
 * @param id        , id of the patient.
 * @param p         , patient info to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdatePatientInfoEvent(
  id: PatientID,
  p: Patient,
  eventID: Int = EventType.UPDATE_PATIENT_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of medical record.
 *
 * @param id        , id of the patient.
 * @param m         , medical record to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertMedicalRecordEvent(
  id: PatientID,
  m: MedicalRecord,
  eventID: Int = EventType.INSERT_MEDICAL_RECORD.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of medical record.
 *
 * @param id        , id of the patient.
 * @param m         , medical record to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateMedicalRecordEvent(
  id: PatientID,
  m: MedicalRecord,
  eventID: Int = EventType.UPDATE_MEDICAL_RECORD.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of general info.
 *
 * @param id        , id of the patient.
 * @param g         , general info to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertGeneralInfoEvent(
  id: PatientID,
  g: GeneralInfo,
  eventID: Int = EventType.INSERT_GENERAL_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of general info.
 *
 * @param id        , id of the patient.
 * @param g         , general info to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateGeneralInfoEvent(
  id: PatientID,
  g: GeneralInfo,
  eventID: Int = EventType.UPDATE_GENERAL_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of general practitioner info.
 *
 * @param id        , id of the patient.
 * @param g         , general practitioner info to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertGeneralPractitionerInfoEvent(
  id: PatientID,
  g: GeneralPractitionerInfo,
  eventID: Int = EventType.INSERT_GENERAL_PRACTITIONER_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of general practitioner info.
 *
 * @param id        , id of the patient.
 * @param g         , general practitioner info to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateGeneralPractitionerInfoEvent(
  id: PatientID,
  g: GeneralPractitionerInfo,
  eventID: Int = EventType.UPDATE_GENERAL_PRACTITIONER_INFO.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of cardiology prediction.
 *
 * @param id        , id of the doctor.
 * @param c         , cardiology prediction to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertCardiologyPredictionsEvent(
  id: DoctorID,
  c: CardiologyPrediction,
  eventID: Int = EventType.INSERT_CARDIOLOGY_PREDICTION.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of cardiology prediction.
 *
 * @param id        , id of the doctor.
 * @param c         , cardiology prediction to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateCardiologyPredictionsEvent(
  id: DoctorID,
  c: CardiologyPrediction,
  eventID: Int = EventType.UPDATE_CARDIOLOGY_PREDICTION.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of insert of cardiology visit.
 *
 * @param id        , id of the doctor.
 * @param c         , cardiology visit to insert.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class InsertCardiologyVisitEvent(
  id: PatientID,
  c: CardiologyVisit,
  eventID: Int = EventType.INSERT_CARDIOLOGY_VISIT.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)

/**
 * Event of update of cardiology visit.
 *
 * @param id        , id of the doctor.
 * @param c         , cardiology visit to update.
 * @param eventID   , id of the event.
 * @param timestamp , timestamp of the event.
 */
case class UpdateCardiologyVisitEvent(
  id: PatientID,
  c: CardiologyVisit,
  eventID: Int = EventType.UPDATE_CARDIOLOGY_VISIT.id,
  timestamp: LocalDateTime = LocalDateTime.now()
) extends Event(timestamp, id)
