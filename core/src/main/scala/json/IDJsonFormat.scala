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

package json

import domainmodel.{DoctorID, PatientID}
import spray.json.DefaultJsonProtocol.{StringJsonFormat, jsonFormat1}
import spray.json.RootJsonFormat

/**
 * Json format for id's object.
 */
object IDJsonFormat {

  /**
   * Implicit for patient id object.
   */
  implicit lazy val patientIDJsonFormat: RootJsonFormat[PatientID] =
    jsonFormat1(PatientID)

  /**
   * Implicit for doctor id object.
   */
  implicit lazy val doctorIDJsonFormat: RootJsonFormat[DoctorID] = jsonFormat1(
    DoctorID
  )

}
