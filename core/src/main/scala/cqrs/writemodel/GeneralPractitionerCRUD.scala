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

import cqrs.writemodel.WriteModel.{
  cardiologyPredictionsCollection,
  doctorsCollection,
  generalPractitionerInfoCollection,
  patientsCollection
}
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.{DoctorID, PatientID}
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.generalpractitionerinfo.GeneralPractitionerInfoJsonFormat.generalPractitionerInfoJsonFormat
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.{and, equal}
import org.mongodb.scala.model.Updates.set
import spray.json.enrichAny

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for general practitioner.
 */
class GeneralPractitionerCRUD {

  /**
   * This method is used to insert a new general practitioner info in the database.
   *
   * @param generalpractitionerinfo general practitioner info to insert
   * @return string representing the result
   */
  def insertGeneralPractitionerInfo(
    generalpractitionerinfo: GeneralPractitionerInfo
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(generalpractitionerinfo.toJson.compactPrint)
    val patientID: BsonDocument =
      BsonDocument.apply(generalpractitionerinfo.patientID.toJson.compactPrint)

    val doctor: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val patient = Await.result(
      patientsCollection.find(equal("patientID", patientID)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    if (doctor.nonEmpty) {
      if (patient.nonEmpty) {
        Await.result(
          generalPractitionerInfoCollection.insertOne(document).toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )
        Await.result(
          patientsCollection
            .findOneAndUpdate(
              equal("patientID", patientID),
              set("generalPractitionerInfo", document)
            )
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )

        "General practitioner info created."
      } else {
        "Error! Patient not exist!"
      }
    } else "Error! Doctor not exist!"
  }

  /**
   * This method is used to update an existing general practitioner info in the database.
   *
   * @param patientID               patient's id
   * @param generalpractitionerinfo medical practitioner info updated
   * @return string representing the result
   */
  def updateGeneralPractitionerInfo(
    patientID: PatientID,
    generalpractitionerinfo: GeneralPractitionerInfo
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(generalpractitionerinfo.toJson.compactPrint)
    val id: BsonDocument = BsonDocument.apply(patientID.toJson.compactPrint)
    val doctorID: BsonDocument =
      BsonDocument.apply(generalpractitionerinfo.doctorID.toJson.compactPrint)

    val patient = Await.result(
      patientsCollection.find(equal("patientID", id)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val doctor = Await.result(
      doctorsCollection
        .find(equal("doctorID", doctorID))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    if (patient.nonEmpty) {
      if (doctor.nonEmpty) {
        val generalPractitionerInfoFinding = Await.result(
          generalPractitionerInfoCollection
            .find(and(equal("patientID", id), equal("doctorID", doctorID)))
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )

        if (generalPractitionerInfoFinding.nonEmpty) {

          Await.result(
            generalPractitionerInfoCollection
              .findOneAndUpdate(
                and(equal("patientID", id), equal("doctorID", doctorID)),
                set("generalPractitionerInfo", document)
              )
              .toFuture(),
            Duration(1, TimeUnit.SECONDS)
          )

          Await.result(
            patientsCollection
              .findOneAndUpdate(
                equal("patientID", id),
                set("generalPractitionerInfo", document)
              )
              .toFuture(),
            Duration(1, TimeUnit.SECONDS)
          )

          "General practitioner info updated."

        } else "Error in update"
      } else "Error Doctor not exist!."
    } else "Error! Patient not exist!"
  }

  /**
   * This method is used to update an existing predictions in the database.
   *
   * @param doctorID doctor's id
   * @return string representing the result
   */
  def updatePredictions(doctorID: DoctorID): String = {
    val count = Await.result(
      cardiologyPredictionsCollection
        .countDocuments(and(equal("doctorID", doctorID), equal("seen", false)))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    val res = Await.result(
      cardiologyPredictionsCollection
        .updateMany(
          and(equal("doctorID", doctorID), equal("seen", false)),
          set("seen", true)
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.getModifiedCount == count) return "Previsions updated."
    "Error in the previsions update."
  }
}
