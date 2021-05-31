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

import cqrs.writemodel.WriteModel.{medicalRecordsCollection, patientsCollection}
import domainmodel.medicalrecords.{MedicalRecord, MedicalRecordsID}
import json.IDJsonFormat.patientIDJsonFormat
import json.medicalrecords.MedicalRecordJsonFormat.{
  medicalRecordJsonFormat,
  medicalRecordsIDJsonFormat
}
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.{and, equal}
import org.mongodb.scala.model.Updates.set
import spray.json.enrichAny

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for rescuer.
 */
class RescuerCRUD {

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
    println("here")
    val document: BsonDocument =
      BsonDocument.apply(medicalRecord.toJson.compactPrint)
    val id: BsonDocument =
      BsonDocument.apply(medicalRecordID.toJson.compactPrint)
    val medicalRecordFind = Await.result(
      medicalRecordsCollection.find(equal("medicalRecordID", id)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    println("t/f", medicalRecordFind.nonEmpty)
    println("medicalRfI", medicalRecordFind)
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

        "Medical record updated.Rescuer"
      } else {
        "Error in the update."
      }
    } else "Error! Medical record not exist"
  }
}
