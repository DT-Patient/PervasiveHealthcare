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
  cardiologyVisitsCollection,
  doctorsCollection,
  patientsCollection
}
import domainmodel.CardiologyVisit
import json.CardiologyVisitJsonFormat.{
  cardiologyVisitIDJsonFormat,
  cardiologyVisitJsonFormat
}
import json.IDJsonFormat.patientIDJsonFormat
import json.RequestJsonFormats.RootJsObjectFormat
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates._
import spray.json.{JsArray, JsObject, enrichAny}

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * This class represent the implementation of CRUD (Create, Read, Update, Delete) for cardiologist.
 */
class CardiologistCRUD {

  /**
   * This method is used to insert a new cardiology visit in the database.
   *
   * @param cardiologyVisit cardiology visit to insert
   * @return string representing the result
   */
  def insertCardiologyVisit(cardiologyVisit: CardiologyVisit): String = {
    val document: BsonDocument =
      BsonDocument.apply(cardiologyVisit.toJson.compactPrint)

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
            BsonDocument.apply(cardiologyVisit.patientID.toJson.compactPrint)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    val record = Await.result(
      cardiologyVisitsCollection
        .find(
          equal(
            "cardiologyVisitID",
            BsonDocument.apply(
              cardiologyVisit.cardiologyVisitID.toJson.compactPrint
            )
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )

    if (doctor.nonEmpty && patient.nonEmpty && record.isEmpty) {

      if (
        !patient.head
          .get("cardiologyVisitHistory")
          .asDocument()
          .containsKey("history")
      ) {
        val newHistory = BsonDocument.apply(
          JsObject(
            "history" -> JsArray(cardiologyVisit.toJson)
          ).toJson.compactPrint
        )
        Await.result(
          patientsCollection
            .findOneAndUpdate(
              equal(
                "patientID",
                BsonDocument.apply(
                  cardiologyVisit.patientID.toJson.compactPrint
                )
              ),
              set("cardiologyVisitHistory", newHistory)
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
                BsonDocument.apply(
                  cardiologyVisit.patientID.toJson.compactPrint
                )
              ),
              push("cardiologyVisitHistory.history", document)
            )
            .toFuture(),
          Duration(1, TimeUnit.SECONDS)
        )

      }
      Await.result(
        cardiologyVisitsCollection.insertOne(document).toFuture(),
        Duration(1, TimeUnit.SECONDS)
      )
      "Cardiology report created."
    } else {
      "Error! This cardiology report already exists!"
    }
  }

}
