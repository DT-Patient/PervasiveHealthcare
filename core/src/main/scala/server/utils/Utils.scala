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
package server.utils

import domainmodel.DoctorID
import domainmodel.professionalfigure.Surgeon

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

  final case class Summary(items: Map[DoctorID, Surgeon])

  /**
   * Get hash password.
   *
   * @param password , password to hash.
   * @return hashed password.
   */
  def getHashedPassword(password: String): String =
    String.format(
      "%032x",
      new BigInteger(
        1,
        MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))
      )
    )

}
