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
  patientsCollection
}
import domainmodel.CardiologyPrediction
import json.CardiologyPredictionJsonFormat.cardiologyPredictionJsonFormat
import json.IDJsonFormat.patientIDJsonFormat
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.equal
import spray.json.enrichAny

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for patient.
 */
class PatientCRUD {

  /**
   * This method is used to insert a new cardiology prediction in the database.
   *
   * @param cardiologyPrediction cardiology prediction to insert
   * @return string representing the result
   */
  def insertCardiologyPrediction(
    cardiologyPrediction: CardiologyPrediction
  ): String = {
    val document: BsonDocument =
      BsonDocument.apply(cardiologyPrediction.toJson.compactPrint)

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
            BsonDocument.apply(
              cardiologyPrediction.patientID.toJson.compactPrint
            )
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    if (doctor.nonEmpty && patient.nonEmpty) {
      Await.result(
        cardiologyPredictionsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      "Cardiology prediction created."
    } else {
      "Error! Doctor and/or patient don't exist!"
    }

  }

}
