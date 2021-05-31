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

import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

object WriteModel {

  /**
   * Database.
   */
  val database: MongoDatabase = MongoClient().getDatabase("WriteModel")

  /**
   * Auth Collection.
   */
  val authCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("credentials")

  /**
   * Doctors Collection.
   */
  val doctorsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("doctors")

  /**
   * Medical Records Collection.
   */
  val medicalRecordsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("medicalrecords")

  /**
   * Patients Collection.
   */
  val patientsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("patients")

  /**
   * Clinical Diaries Collection.
   */
  val clinicalDiariesCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("clinicalDiaries")

  /**
   * Discharge Letter Collection.
   */
  val dischargeLetterCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("dischargeLetters")

  /**
   * Visits Collection.
   */
  val visitsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("visits")

  /**
   * Prescriptions Collection.
   */
  val prescriptionsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("prescriptions")

  /**
   * General Practitioner Info Collection.
   */
  val generalPractitionerInfoCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("generalpractitionerinfo")

  /**
   * Cardiology Visits Collection.
   */
  val cardiologyVisitsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("cardiologyvisits")

  /**
   * Cardiology Predictions Collection.
   */
  val cardiologyPredictionsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("cardiologypredictions")

}
