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

package server.models

import cqrs.writemodel.Role
import server.models.JwtAuthentication.Tokens.Tokens

object JwtAuthentication {

  var tokens: Tokens = Tokens()

  /**
   * Token.
   *
   * @param token , a token.
   */
  case class Token(token: (String, Int))

  object Tokens {

    /**
     * Collections of tokens.
     *
     * @param tokens , tokens in collection.
     */
    case class Tokens private (tokens: Map[String, Int] = Map.empty) {

      /**
       * Add token.
       *
       * @param token , token to add.
       * @return new collection of token.
       */
      def addNewToken(token: Token): Tokens = Tokens(tokens + token.token)

      /**
       * Remove token.
       *
       * @param tokenId , token to remove.
       * @return new collection of token.
       */
      def removeToken(tokenId: String): Tokens =
        Tokens(tokens.filter(t => t._1 != tokenId))
    }

    def apply(): Tokens = Tokens()

  }

  /**
   * Has Admin Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasAdminPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && tokens.tokens(token) == Role.ADMIN.id

  /**
   * Has Doctor Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasDoctorPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && tokens.tokens(token) != Role.PATIENT.id

  /**
   * Has Hospital Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasHospitalPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && tokens.tokens(
      token
    ) == Role.SURGEON.id || tokens.tokens(token) == Role.WARD_NURSE.id || tokens
      .tokens(token) == Role.INSTRUMENTALIST.id || tokens.tokens(
      token
    ) == Role.ANESTHETIST.id

  /**
   * Has Rescuer Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasRescuerPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && tokens.tokens(token) == Role.RESCUER.id

  /**
   * Has Cardiologist Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasCardiologistPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && tokens.tokens(
      token
    ) == Role.CARDIOLOGIST.id

  /**
   * Is Logged.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def isLogged(token: String): Boolean = tokens.tokens.contains(token)

  /**
   * Has Common Permissions.
   *
   * @param token , token to authentication.
   * @return if have specific permission.
   */
  def hasCommonPermissions(token: String): Boolean =
    tokens.tokens.contains(token) && Role.maxId >= tokens.tokens(token)
}
