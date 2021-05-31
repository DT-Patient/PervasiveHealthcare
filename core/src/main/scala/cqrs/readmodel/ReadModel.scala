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

package cqrs.readmodel

import cqrs.readmodel.eventsourcing._
import cqrs.writemodel.Role.Role
import domainmodel.ChestPainType.ChestPainType
import domainmodel.Gender.Gender
import domainmodel.KinshipDegree.KinshipDegree
import domainmodel.Patient.Patient
import domainmodel.RestingElectrocardiographic.RestingElectrocardiographic
import domainmodel.SlopeST.SlopeST
import domainmodel.Thal.Thal
import domainmodel.generalinfo.AllergyClass.AllergyClass
import domainmodel.generalinfo.BloodType.BloodType
import domainmodel.generalinfo.GeneralInfo
import domainmodel.generalinfo.Rh.Rh
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.medicalrecords.MedicalRecord
import domainmodel.professionalfigure.Specialization.Specialization
import domainmodel.professionalfigure._
import domainmodel.{CardiologyPrediction, CardiologyVisit, DoctorID, PatientID}
import org.mongodb.scala.{MongoClient, MongoDatabase}

object ReadModel {

  val database: MongoDatabase = MongoClient().getDatabase("ReadModel")

  /**
   * Method to initialize read model.
   */
  def initialize(): Unit = {
    RoleCollection.initialize()
    GenderCollection.initialize()
    AllergyClassCollection.initialize()
    BloodTypeCollection.initialize()
    RhCollection.initialize()
    SpecializationCollection.initialize()
    KinshipDegreeCollection.initialize()
    ChestPainTypeCollection.initialize()
    RestingElectrocardiographicCollection.initialize()
    SlopeSTCollection.initialize()
    ThalCollection.initialize()
  }

  /**
   * Get all role in system.
   *
   * @return all role.
   */
  def getRole: Set[Role] = {
    RoleCollection.get()
  }

  /**
   * Get all gender in system.
   *
   * @return all gender.
   */
  def getGender: Set[Gender] = {
    GenderCollection.get()
  }

  /**
   * Get all allergy in system.
   *
   * @return all allergy.
   */
  def getAllergy: Set[AllergyClass] = {
    AllergyClassCollection.get()
  }

  /**
   * Get all blood type in system.
   *
   * @return all blood type.
   */
  def getBloodType: Set[BloodType] = {
    BloodTypeCollection.get()
  }

  /**
   * Get all rh in system.
   *
   * @return all rh.
   */
  def getRh: Set[Rh] = {
    RhCollection.get()
  }

  /**
   * Get all specialization in system.
   *
   * @return all specialization.
   */
  def getSpecialization: Set[Specialization] = {
    SpecializationCollection.get()
  }

  /**
   * Get all kinship degree in system.
   *
   * @return all kinship degree.
   */
  def getKinshipDegree: Set[KinshipDegree] = {
    KinshipDegreeCollection.get()
  }

  /**
   * Get all chest pain types in system.
   *
   * @return all chest pain types.
   */
  def getChestPainType: Set[ChestPainType] = {
    ChestPainTypeCollection.get()
  }

  /**
   * Get all resting electrocardiographic values in system.
   *
   * @return all resting electrocardiographic values.
   */
  def getRestingECG: Set[RestingElectrocardiographic] = {
    RestingElectrocardiographicCollection.get()
  }

  /**
   * Get all slope ST values in system.
   *
   * @return all slope ST values.
   */
  def getSlopeST: Set[SlopeST] = {
    SlopeSTCollection.get()
  }

  /**
   * Get all defect types in system.
   *
   * @return all defect types.
   */
  def getThal: Set[Thal] = {
    ThalCollection.get()
  }

  /**
   * Create surgeon in read model with event sourcing.
   *
   * @param surgeon , surgeon to save.
   */
  def createSurgeon(surgeon: Surgeon): Unit = {
    EventStore.addEvent(InsertSurgeonEvent(surgeon.doctorID, surgeon))
  }

  /**
   * Update surgeon in read model with event sourcing.
   *
   * @param surgeon , surgeon to save.
   */
  def updateSurgeon(surgeon: Surgeon): Unit = {
    EventStore.addEvent(UpdateSurgeonEvent(surgeon.doctorID, surgeon))
  }

  /**
   * Create instrumentalist in read model with event sourcing.
   *
   * @param instrumentalist , instrumentalist to save.
   */
  def createInstrumentalist(instrumentalist: Instrumentalist): Unit = {
    EventStore.addEvent(
      InsertInstrumentalistEvent(instrumentalist.doctorID, instrumentalist)
    )
  }

  /**
   * Update instrumentalist in read model with event sourcing.
   *
   * @param instrumentalist , instrumentalist to save.
   */
  def updateInstrumentalist(instrumentalist: Instrumentalist): Unit = {
    EventStore.addEvent(
      UpdateInstrumentalistEvent(instrumentalist.doctorID, instrumentalist)
    )
  }

  /**
   * Create anesthetist in read model with event sourcing.
   *
   * @param anesthetist , anesthetist to save.
   */
  def createAnesthetist(anesthetist: Anesthetist): Unit = {
    EventStore.addEvent(
      InsertAnesthetistEvent(anesthetist.doctorID, anesthetist)
    )
  }

  /**
   * Update anesthetist in read model with event sourcing.
   *
   * @param anesthetist , anesthetist to save.
   */
  def updateAnesthetist(anesthetist: Anesthetist): Unit = {
    EventStore.addEvent(
      UpdateAnesthetistEvent(anesthetist.doctorID, anesthetist)
    )
  }

  /**
   * Create general practitioner in read model with event sourcing.
   *
   * @param generalPractitioner , general practitioner to save.
   */
  def createGeneralPractitioner(
    generalPractitioner: GeneralPractitioner
  ): Unit = {
    EventStore.addEvent(
      InsertGeneralPractitionerEvent(
        generalPractitioner.doctorID,
        generalPractitioner
      )
    )
  }

  /**
   * Update general practitioner in read model with event sourcing.
   *
   * @param generalPractitioner , general practitioner to save.
   */
  def updateGeneralPractitioner(
    generalPractitioner: GeneralPractitioner
  ): Unit = {
    EventStore.addEvent(
      UpdateGeneralPractitionerEvent(
        generalPractitioner.doctorID,
        generalPractitioner
      )
    )
  }

  /**
   * Create ward nurse in read model with event sourcing.
   *
   * @param wardNurse , ward nurse to save.
   */
  def createWardNurse(wardNurse: WardNurse): Unit = {
    EventStore.addEvent(InsertWardNurseEvent(wardNurse.doctorID, wardNurse))
  }

  /**
   * update ward nurse in read model with event sourcing.
   *
   * @param wardNurse , ward nurse to save.
   */
  def updateWardNurse(wardNurse: WardNurse): Unit = {
    EventStore.addEvent(UpdateWardNurseEvent(wardNurse.doctorID, wardNurse))
  }

  /**
   * Create rescuer in read model with event sourcing.
   *
   * @param rescuer , rescuer to save.
   */
  def createRescuer(rescuer: Rescuer): Unit = {
    EventStore.addEvent(InsertRescuerEvent(rescuer.doctorID, rescuer))
  }

  /**
   * Update rescuer in read model with event sourcing.
   *
   * @param rescuer , rescuer to save.
   */
  def updateRescuer(rescuer: Rescuer): Unit = {
    EventStore.addEvent(UpdateRescuerEvent(rescuer.doctorID, rescuer))
  }

  /**
   * Create cardiologist in read model with event sourcing.
   *
   * @param cardiologist , cardiologist to save.
   */
  def createCardiologist(cardiologist: Cardiologist): Unit = {
    EventStore.addEvent(
      InsertCardiologistEvent(cardiologist.doctorID, cardiologist)
    )
  }

  /**
   * Update cardiologist in read model with event sourcing.
   *
   * @param cardiologist , cardiologist to save.
   */
  def updateCardiologist(cardiologist: Cardiologist): Unit = {
    EventStore.addEvent(
      UpdateCardiologistEvent(cardiologist.doctorID, cardiologist)
    )
  }

  //PATIENT

  /**
   * Insert patient in read model with event sourcing.
   *
   * @param patient , patient to save.
   */
  def insertPatient(patient: Patient): Unit = {
    EventStore.addEvent(InsertPatientInfoEvent(patient.patientID, patient))
  }

  /**
   * Update patient in read model with event sourcing.
   *
   * @param patient , patient to save.
   */
  def updatePatientInfo(patient: Patient): Unit = {
    EventStore.addEvent(UpdatePatientInfoEvent(patient.patientID, patient))
  }

  /**
   * Insert medical record in read model with event sourcing.
   *
   * @param patientID     , id of the patient.
   * @param medicalRecord , medical record to add.
   */
  def insertMedicalRecord(
    patientID: PatientID,
    medicalRecord: MedicalRecord
  ): Unit = {
    EventStore.addEvent(InsertMedicalRecordEvent(patientID, medicalRecord))
  }

  /**
   * Update medical record in read model with event sourcing.
   *
   * @param patientID     , id of the patient.
   * @param medicalRecord , medical record to add.
   */
  def updateMedicalRecord(
    patientID: PatientID,
    medicalRecord: MedicalRecord
  ): Unit = {
    EventStore.addEvent(UpdateMedicalRecordEvent(patientID, medicalRecord))
  }

  /**
   * Insert general info in read model with event sourcing.
   *
   * @param patientID   , id of the patient.
   * @param generalInfo , general info to add.
   */
  def insertGeneralInfo(
    patientID: PatientID,
    generalInfo: GeneralInfo
  ): Unit = {
    EventStore.addEvent(InsertGeneralInfoEvent(patientID, generalInfo))
  }

  /**
   * Update general info in read model with event sourcing.
   *
   * @param patientID   , id of the patient.
   * @param generalInfo , general info to add.
   */
  def updateGeneralInfo(
    patientID: PatientID,
    generalInfo: GeneralInfo
  ): Unit = {
    EventStore.addEvent(UpdateGeneralInfoEvent(patientID, generalInfo))
  }

  /**
   * Insert general practitioner info in read model with event sourcing.
   *
   * @param patientID               , id of the patient.
   * @param generalPractitionerInfo , general practitioner info to add.
   */
  def insertGeneralPractitionerInfo(
    patientID: PatientID,
    generalPractitionerInfo: GeneralPractitionerInfo
  ): Unit = {
    EventStore.addEvent(
      InsertGeneralPractitionerInfoEvent(patientID, generalPractitionerInfo)
    )
  }

  /**
   * Update general practitioner info in read model with event sourcing.
   *
   * @param patientID               , id of the patient.
   * @param generalPractitionerInfo , general practitioner info to add.
   */
  def updateGeneralPractitionerInfo(
    patientID: PatientID,
    generalPractitionerInfo: GeneralPractitionerInfo
  ): Unit = {
    EventStore.addEvent(
      UpdateGeneralPractitionerInfoEvent(patientID, generalPractitionerInfo)
    )
  }

  /**
   * Insert cardiology visit in read model with event sourcing.
   *
   * @param patientID       , id of the patient.
   * @param cardiologyVisit , cardiology visit to add.
   */
  def insertCardiologyVisit(
    patientID: PatientID,
    cardiologyVisit: CardiologyVisit
  ): Unit = {
    EventStore.addEvent(InsertCardiologyVisitEvent(patientID, cardiologyVisit))
  }

  /**
   * Update cardiology visit in read model with event sourcing.
   *
   * @param patientID       , id of the patient.
   * @param cardiologyVisit , cardiology visit to add.
   */
  def updateCardiologyVisit(
    patientID: PatientID,
    cardiologyVisit: CardiologyVisit
  ): Unit = {
    EventStore.addEvent(UpdateCardiologyVisitEvent(patientID, cardiologyVisit))
  }

  /**
   * Insert cardiology prediction in read model with event sourcing.
   *
   * @param doctorID             , id of the doctor.
   * @param cardiologyPrediction , cardiology prediction to add.
   */
  def insertCardiologyPrediction(
    doctorID: DoctorID,
    cardiologyPrediction: CardiologyPrediction
  ): Unit = {
    EventStore.addEvent(
      InsertCardiologyPredictionsEvent(doctorID, cardiologyPrediction)
    )
  }

  /**
   * Update cardiology prediction in read model with event sourcing.
   *
   * @param doctorID , id of the doctor.
   */
  def updateCardiologyPrediction(doctorID: DoctorID): Unit = {
    val predictions = RMUtility.getNewPredictions(doctorID)
    predictions
      .filter(!_.seen)
      .foreach(x =>
        EventStore.addEvent(
          UpdateCardiologyPredictionsEvent(
            doctorID,
            CardiologyPrediction(
              x.patientID,
              x.doctorID,
              x.cardiologyVisit,
              seen = true
            )
          )
        )
      )

  }

}
