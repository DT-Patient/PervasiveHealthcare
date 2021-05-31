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
  doctorsCollection,
  medicalRecordsCollection,
  patientsCollection
}
import domainmodel.medicalrecords.{MedicalRecord, MedicalRecordsID}
import json.IDJsonFormat.patientIDJsonFormat
import json.RequestJsonFormats.RootJsObjectFormat
import json.medicalrecords.MedicalRecordJsonFormat.{
  medicalRecordJsonFormat,
  medicalRecordsIDJsonFormat
}
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.{and, equal}
import org.mongodb.scala.model.Updates.{push, set}
import spray.json.{JsArray, JsObject, enrichAny}

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for surgeon.
 */
class SurgeonCRUD {

  /**
   * This method is used to insert a new medical record in the database.
   *
   * @param medicalRecord medical record to insert
   * @return string representing the result
   */
  def insertMedicalRecord(medicalRecord: MedicalRecord): String = {

    val document: BsonDocument =
      BsonDocument.apply(medicalRecord.toJson.compactPrint)

    val doctor: Seq[BsonDocument] = Await.result(
      doctorsCollection
        .find(equal("doctorID", document.get("doctorID")))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val patient = Await.result(
      patientsCollection
        .find(
          equal(
            "patientID",
            BsonDocument.apply(medicalRecord.patientID.toJson.compactPrint)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val record = Await.result(
      medicalRecordsCollection
        .find(
          equal(
            "medicalRecordID",
            BsonDocument.apply(
              medicalRecord.medicalRecordID.toJson.compactPrint
            )
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    if (doctor.nonEmpty && patient.nonEmpty && record.isEmpty) {
      if (
        !patient.head.get("medicalRecords").asDocument().containsKey("history")
      ) {
        val newHistory = BsonDocument.apply(
          JsObject(
            "history" -> JsArray(medicalRecord.toJson)
          ).toJson.compactPrint
        )
        Await.result(
          patientsCollection
            .findOneAndUpdate(
              equal(
                "patientID",
                BsonDocument.apply(medicalRecord.patientID.toJson.compactPrint)
              ),
              set("medicalRecords", newHistory)
            )
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )
      } else {
        Await.result(
          patientsCollection
            .findOneAndUpdate(
              equal(
                "patientID",
                BsonDocument.apply(medicalRecord.patientID.toJson.compactPrint)
              ),
              push("medicalRecords.history", document)
            )
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )

      }
      Await.result(
        medicalRecordsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      "Medical record created."
    } else {
      "Error! This medical record already exists!"
    }
  }

  /**
   * This method is used to update an existing medical record in the database.
   *
   * @param medicalRecordID medical record's id
   * @param medicalRecord   medical record updated
   * @return string representing the result
   */
  def updateMedicalRecord(
    medicalRecordID: MedicalRecordsID,
    medicalRecord: MedicalRecord
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(medicalRecord.toJson.compactPrint)
    val id: BsonDocument =
      BsonDocument.apply(medicalRecordID.toJson.compactPrint)
    val medicalRecordFind = Await.result(
      medicalRecordsCollection.find(equal("medicalRecordID", id)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val patient = Await.result(
      patientsCollection
        .find(
          equal(
            "patientID",
            BsonDocument.apply(medicalRecord.patientID.toJson.compactPrint)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (medicalRecordFind.nonEmpty) {
      if (
        patient.nonEmpty && patient.head
          .get("medicalRecords")
          .asDocument()
          .containsKey("history")
      ) {

        Await.result(
          medicalRecordsCollection
            .findOneAndReplace(equal("medicalRecordID", id), document)
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )
        Await.result(
          patientsCollection
            .findOneAndUpdate(
              and(
                equal(
                  "patientID",
                  BsonDocument.apply(
                    medicalRecord.patientID.toJson.compactPrint
                  )
                ),
                equal("medicalRecords.history.medicalRecordID", id)
              ),
              set("medicalRecords.history.$", document)
            )
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )

        "Medical record updated."
      } else {
        "Error in the update."
      }
    } else "Error! Medical record not exist"
  }

}
