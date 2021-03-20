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

package database

import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase, Observable}
import org.mongodb.scala.bson.BsonDocument

object WriteModel {

  val database: MongoDatabase = MongoClient().getDatabase("WriteModel")

  val patientsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("patient")

  val surgeonsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("surgeons")


}


