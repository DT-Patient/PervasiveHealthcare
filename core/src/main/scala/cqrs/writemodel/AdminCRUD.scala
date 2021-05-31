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

package cqrs.writemodel

import cqrs.writemodel.WriteModel.{doctorsCollection, patientsCollection}
import domainmodel.Patient.Patient
import domainmodel.professionalfigure._
import domainmodel.{DoctorID, PatientID, User}
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.PatientJsonFormat.PatientJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat._
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.equal
import spray.json.enrichAny

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for admin.
 */
class AdminCRUD {

  /**
   * This method is used to insert a new professional figure, surgeon, in this case, in the database.
   *
   * @param surgeon professional figure to insert
   * @return string representing the result
   */
  def insertSurgeon(surgeon: Surgeon): String = {
    val document: BsonDocument = BsonDocument.apply(surgeon.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth
          .signUp(User(surgeon.doctorID.value, "surgeon"), Role.SURGEON),
        Duration(1, TimeUnit.SECONDS)
      )
      "Surgeon created."
    } else {
      "Error! Surgeon with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, surgeon, in this case, in the database.
   *
   * @param doctorID professional figure's id
   * @param surgeon  professional figure updated
   * @return string representing the result
   */
  def updateSurgeon(doctorID: DoctorID, surgeon: Surgeon): String = {
    val document: BsonDocument = BsonDocument.apply(surgeon.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Surgeon updated."
  }

  /**
   * This method is used to insert a new professional figure, anesthetist, in this case, in the database.
   *
   * @param anesthetist professional figure to insert
   * @return string representing the result
   */
  def insertAnesthetist(anesthetist: Anesthetist): String = {
    val document: BsonDocument =
      BsonDocument.apply(anesthetist.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth.signUp(
          User(anesthetist.doctorID.value, "anesthetist"),
          Role.ANESTHETIST
        ),
        Duration(1, TimeUnit.SECONDS)
      )
      "Anesthetist created."
    } else {
      "Error! Anesthetist with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, anesthetist, in this case, in the database.
   *
   * @param doctorID    professional figure's id
   * @param anesthetist professional figure updated
   * @return string representing the result
   */
  def updateAnesthetist(
    doctorID: DoctorID,
    anesthetist: Anesthetist
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(anesthetist.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Anesthetist updated."
  }

  /**
   * This method is used to insert a new professional figure, instrumentalist, in this case, in the database.
   *
   * @param instrumentalist professional figure to insert
   * @return string representing the result
   */
  def insertInstrumentalist(instrumentalist: Instrumentalist): String = {
    val document: BsonDocument =
      BsonDocument.apply(instrumentalist.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth.signUp(
          User(instrumentalist.doctorID.value, "instrumentalist"),
          Role.INSTRUMENTALIST
        ),
        Duration(1, TimeUnit.SECONDS)
      )
      "Instrumentalist created."
    } else {
      "Error! Instrumentalist with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, instrumentalist, in this case, in the database.
   *
   * @param doctorID        professional figure's id
   * @param instrumentalist professional figure updated
   * @return string representing the result
   */
  def updateInstrumentalist(
    doctorID: DoctorID,
    instrumentalist: Instrumentalist
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(instrumentalist.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Instrumentalist updated."
  }

  /**
   * This method is used to insert a new professional figure, cardiologist, in this case, in the database.
   *
   * @param cardiologist professional figure to insert
   * @return string representing the result
   */
  def insertCardiologist(cardiologist: Cardiologist): String = {
    val document: BsonDocument =
      BsonDocument.apply(cardiologist.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth.signUp(
          User(cardiologist.doctorID.value, "cardiologist"),
          Role.CARDIOLOGIST
        ),
        Duration(1, TimeUnit.SECONDS)
      )
      "Cardiologist created."
    } else {
      "Error! Cardiologist with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, cardiologist, in this case, in the database.
   *
   * @param doctorID     professional figure's id
   * @param cardiologist professional figure updated
   * @return string representing the result
   */
  def updateCardiologist(
    doctorID: DoctorID,
    cardiologist: Cardiologist
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(cardiologist.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Cardiologist updated."
  }

  /**
   * This method is used to insert a new professional figure, general practitioner, in this case, in the database.
   *
   * @param generalPractitioner professional figure to insert
   * @return string representing the result
   */
  def insertGeneralPractitioner(
    generalPractitioner: GeneralPractitioner
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(generalPractitioner.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth.signUp(
          User(generalPractitioner.doctorID.value, "generalpractitioner"),
          Role.GENERAL_PRACTITIONER
        ),
        Duration(1, TimeUnit.SECONDS)
      )
      "General practitioner created."
    } else {
      "Error! General practitioner with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, general practitioner, in this case, in the database.
   *
   * @param doctorID            professional figure's id
   * @param generalPractitioner professional figure updated
   * @return string representing the result
   */
  def updateGeneralPractitioner(
    doctorID: DoctorID,
    generalPractitioner: GeneralPractitioner
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(generalPractitioner.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "General practitioner updated."
  }

  /**
   * This method is used to insert a new professional figure, rescuer, in this case, in the database.
   *
   * @param rescuer professional figure to insert
   * @return string representing the result
   */
  def insertRescuer(rescuer: Rescuer): String = {
    val document: BsonDocument = BsonDocument.apply(rescuer.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth
          .signUp(User(rescuer.doctorID.value, "rescuer"), Role.RESCUER),
        Duration(1, TimeUnit.SECONDS)
      )
      "Rescuer created."
    } else {
      "Error! Rescuer with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, rescuer, in this case, in the database.
   *
   * @param doctorID professional figure's id
   * @param rescuer  professional figure updated
   * @return string representing the result
   */
  def updateRescuer(doctorID: DoctorID, rescuer: Rescuer): String = {
    val document: BsonDocument = BsonDocument.apply(rescuer.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Rescuer updated."
  }

  /**
   * This method is used to insert a new professional figure, ward nurse, in this case, in the database.
   *
   * @param wardNurse professional figure to insert
   * @return string representing the result
   */
  def insertWardNurse(wardNurse: WardNurse): String = {
    val document: BsonDocument =
      BsonDocument.apply(wardNurse.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        doctorsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth.signUp(
          User(wardNurse.doctorID.value, "ward_nurse"),
          Role.WARD_NURSE
        ),
        Duration(1, TimeUnit.SECONDS)
      )
      "Ward nurse created."
    } else {
      "Error! Ward nurse with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing professional figure, ward nurse, in this case, in the database.
   *
   * @param doctorID  professional figure's id
   * @param wardNurse professional figure updated
   * @return string representing the result
   */
  def updateWardNurse(doctorID: DoctorID, wardNurse: WardNurse): String = {
    val document: BsonDocument =
      BsonDocument.apply(wardNurse.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(doctorID.toJson.compactPrint)
    Await.result(
      doctorsCollection
        .findOneAndReplace(equal("doctorID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Ward nurse updated."
  }

  /**
   * This method is used to insert a new patient in the database.
   *
   * @param patient patient to insert
   * @return string representing the result
   */
  def insertPatient(patient: Patient): String = {
    val document: BsonDocument = BsonDocument.apply(patient.toJson.compactPrint)

    val res: Seq[BsonDocument] = Await.result(
      patientsCollection
        .find(equal("patientID", document.get("patientID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.isEmpty) {
      Await.result(
        patientsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      Await.result(
        Repository.auth
          .signUp(User(patient.patientID.value, "patient"), Role.PATIENT),
        Duration(1, TimeUnit.SECONDS)
      )
      "Patient created."
    } else {
      "Error! Patient with the same doctorID already exists!"
    }
  }

  /**
   * This method is used to update an existing patient in the database.
   *
   * @param patientID patient's id
   * @param patient   patient updated
   * @return string representing the result
   */
  def updatePatient(patientID: PatientID, patient: Patient): String = {
    val document: BsonDocument = BsonDocument.apply(patient.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(patientID.toJson.compactPrint)
    Await.result(
      patientsCollection
        .findOneAndReplace(equal("patientID", id), document)
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    "Patient updated."
  }
}
