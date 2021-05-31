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

import cqrs.writemodel.Role.Role
import cqrs.writemodel.WriteModel.authCollection
import domainmodel.User
import json.RequestJsonFormats.RootJsObjectFormat
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.result.InsertOneResult
import server.utils.Utils
import spray.json.{JsNumber, JsObject, JsString, enrichAny}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class Auth {

  /**
   * Sign up method.
   *
   * @param user , user.
   * @param role , role of user.
   * @return future of insert one result.
   */
  def signUp(user: User, role: Role): Future[InsertOneResult] = {
    val newUser = User(user.id, Utils.getHashedPassword(user.password))
    val document: BsonDocument = BsonDocument.apply(
      JsObject(
        "id" -> JsString(newUser.id),
        "password" -> JsString(newUser.password),
        "role" -> JsNumber(role.id)
      ).toJson.compactPrint
    )
    authCollection.insertOne(document).toFuture()
  }

  /**
   * Login method.
   *
   * @param user , user.
   * @return an option of role and a string.
   */
  def login(user: User): (Option[Role], String) = {
    val userFound = Await.result(
      authCollection.find(equal("id", user.id)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (userFound.isEmpty) (None, "User not found.")
    else {
      if (userFound.head.get("password").asString().getValue != user.password)
        (None, "Credentials error.")
      else
        (
          Some(Role(userFound.head.get("role").asInt32().getValue)),
          "Login done."
        )
    }
  }
}
