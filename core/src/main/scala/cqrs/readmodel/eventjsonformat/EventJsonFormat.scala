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

package cqrs.readmodel.eventjsonformat

import cqrs.readmodel.eventsourcing._
import json.CardiologyPredictionJsonFormat.cardiologyPredictionJsonFormat
import json.CardiologyVisitJsonFormat.cardiologyVisitJsonFormat
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.LocalDateTimeJsonFormat.DateTimeFormat
import json.PatientJsonFormat.PatientJsonFormat
import json.RequestJsonFormats.{IntJsonFormat, jsonFormat4}
import json.generalinfo.GeneralInfoJsonFormat.generalInfoJsonFormat
import json.generalpractitionerinfo.GeneralPractitionerInfoJsonFormat.generalPractitionerInfoJsonFormat
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat._
import spray.json.RootJsonFormat

/**
 * Json format for event in event sourcing.
 */
object EventJsonFormat {

  /**
   * Insert Surgeon Event Json Format.
   */
  implicit val insertSurgeonEventJsonFormat
    : RootJsonFormat[InsertSurgeonEvent] = jsonFormat4(InsertSurgeonEvent)

  /**
   * Update Surgeon Event Json Format.
   */
  implicit val updateSurgeonEventJsonFormat
    : RootJsonFormat[UpdateSurgeonEvent] = jsonFormat4(UpdateSurgeonEvent)

  /**
   * Insert Instrumentalist Event Json Format.
   */
  implicit val insertInstrumentalistEventJsonFormat
    : RootJsonFormat[InsertInstrumentalistEvent] = jsonFormat4(
    InsertInstrumentalistEvent
  )

  /**
   * Update Instrumentalist Event Json Format.
   */
  implicit val updateInstrumentalistEventJsonFormat
    : RootJsonFormat[UpdateInstrumentalistEvent] = jsonFormat4(
    UpdateInstrumentalistEvent
  )

  /**
   * Insert Anesthetist Event Json Format.
   */
  implicit val insertAnesthetistEventJsonFormat
    : RootJsonFormat[InsertAnesthetistEvent] = jsonFormat4(
    InsertAnesthetistEvent
  )

  /**
   * Update Anesthetist Event Json Format.
   */
  implicit val updateAnesthetistEventJsonFormat
    : RootJsonFormat[UpdateAnesthetistEvent] = jsonFormat4(
    UpdateAnesthetistEvent
  )

  /**
   * Insert General Practitioner Event Json Format.
   */
  implicit val insertGeneralPractitionerEventJsonFormat
    : RootJsonFormat[InsertGeneralPractitionerEvent] = jsonFormat4(
    InsertGeneralPractitionerEvent
  )

  /**
   * Update General Practitioner Event Json Format.
   */
  implicit val updateGeneralPractitionerEventJsonFormat
    : RootJsonFormat[UpdateGeneralPractitionerEvent] = jsonFormat4(
    UpdateGeneralPractitionerEvent
  )

  /**
   * Insert Ward Nurse Event Json Format.
   */
  implicit val insertWardNurseEventJsonFormat
    : RootJsonFormat[InsertWardNurseEvent] = jsonFormat4(InsertWardNurseEvent)

  /**
   * Update Ward Nurse Event Json Format.
   */
  implicit val updateWardNurseEventJsonFormat
    : RootJsonFormat[UpdateWardNurseEvent] = jsonFormat4(UpdateWardNurseEvent)

  /**
   * Insert Rescuer Event Json Format.
   */
  implicit val insertRescuerEventJsonFormat
    : RootJsonFormat[InsertRescuerEvent] = jsonFormat4(InsertRescuerEvent)

  /**
   * Update Rescuer Event Json Format.
   */
  implicit val updateRescuerEventJsonFormat
    : RootJsonFormat[UpdateRescuerEvent] = jsonFormat4(UpdateRescuerEvent)

  /**
   * Insert Cardiologist Event Json Format.
   */
  implicit val insertCardiologistEventJsonFormat
    : RootJsonFormat[InsertCardiologistEvent] = jsonFormat4(
    InsertCardiologistEvent
  )

  /**
   * Update Cardiologist Event Json Format.
   */
  implicit val updateCardiologistEventJsonFormat
    : RootJsonFormat[UpdateCardiologistEvent] = jsonFormat4(
    UpdateCardiologistEvent
  )

  /**
   * Insert Patient Info Event Json Format.
   */
  implicit val insertPatientInfoEventJsonFormat
    : RootJsonFormat[InsertPatientInfoEvent] = jsonFormat4(
    InsertPatientInfoEvent
  )

  /**
   * Update Patient Info Event Json Format.
   */
  implicit val updatePatientInfoEventJsonFormat
    : RootJsonFormat[UpdatePatientInfoEvent] = jsonFormat4(
    UpdatePatientInfoEvent
  )

  /**
   * Insert Medical Record Event Json Format.
   */
  implicit val insertMedicalRecordEventJsonFormat
    : RootJsonFormat[InsertMedicalRecordEvent] = jsonFormat4(
    InsertMedicalRecordEvent
  )

  /**
   * Update Medical Record Event Json Format.
   */
  implicit val updateMedicalRecordEventJsonFormat
    : RootJsonFormat[UpdateMedicalRecordEvent] = jsonFormat4(
    UpdateMedicalRecordEvent
  )

  /**
   * Insert General Info Event Json Format.
   */
  implicit val insertGeneralInfoEventJsonFormat
    : RootJsonFormat[InsertGeneralInfoEvent] = jsonFormat4(
    InsertGeneralInfoEvent
  )

  /**
   * Update General Info Event Json Format.
   */
  implicit val updateGeneralInfoEventJsonFormat
    : RootJsonFormat[UpdateGeneralInfoEvent] = jsonFormat4(
    UpdateGeneralInfoEvent
  )

  /**
   * Insert General Practitioner Info Event Json Format.
   */
  implicit val insertGeneralPractitionerInfoEventJsonFormat
    : RootJsonFormat[InsertGeneralPractitionerInfoEvent] = jsonFormat4(
    InsertGeneralPractitionerInfoEvent
  )

  /**
   * Update General Practitioner Info Event Json Format.
   */
  implicit val updateGeneralPractitionerInfoEventJsonFormat
    : RootJsonFormat[UpdateGeneralPractitionerInfoEvent] = jsonFormat4(
    UpdateGeneralPractitionerInfoEvent
  )

  /**
   * Insert Cardiology Info Event Json Format.
   */
  implicit val insertCardiologyInfoEventJsonFormat
    : RootJsonFormat[InsertCardiologyVisitEvent] = jsonFormat4(
    InsertCardiologyVisitEvent
  )

  /**
   * Update Cardiology Info Event Json Format.
   */
  implicit val updateCardiologyInfoEventJsonFormat
    : RootJsonFormat[UpdateCardiologyVisitEvent] = jsonFormat4(
    UpdateCardiologyVisitEvent
  )

  /**
   * Insert Cardiology Predictions Event Json Format.
   */
  implicit val insertCardiologyPredictionsEventJsonFormat
    : RootJsonFormat[InsertCardiologyPredictionsEvent] = jsonFormat4(
    InsertCardiologyPredictionsEvent
  )

  /**
   * Update Cardiology Predictions Event Json Format.
   */
  implicit val updateCardiologyPredictionsEventJsonFormat
    : RootJsonFormat[UpdateCardiologyPredictionsEvent] = jsonFormat4(
    UpdateCardiologyPredictionsEvent
  )

}
