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

package json.professionalfigure

import domainmodel.DoctorID
import domainmodel.professionalfigure._
import json.IDJsonFormat.doctorIDJsonFormat
import spray.json.{
  DeserializationException,
  JsObject,
  JsString,
  JsValue,
  RootJsonFormat
}

/**
 * Json format for doctor object.
 */
object DoctorJsonFormat {

  /**
   * Implicit for doctor object.
   */
  implicit object DoctorJsonFormat extends RootJsonFormat[Doctor] {

    override def read(json: JsValue): Doctor = {
      json.asJsObject.getFields(
        "doctorID",
        "name",
        "surname",
        "phoneNumber",
        "email",
        "medicalDegreeGrade"
      ) match {
        case Seq(
              _doctorID,
              JsString(_name),
              JsString(_surname),
              JsString(_phoneNumber),
              JsString(_email),
              JsString(_medicalDegreeGrade)
            ) =>
          new Doctor {
            override val doctorID: DoctorID = _doctorID.convertTo[DoctorID]
            override val name: String = _name
            override val surname: String = _surname
            override val phoneNumber: String = _phoneNumber
            override val email: String = _email
            override val medicalDegreeGrade: String = _medicalDegreeGrade
          }
        case _ => throw DeserializationException("Doctor expected")
      }
    }

    override def write(obj: Doctor): JsValue =
      JsObject(
        "doctorID" -> JsObject("value" -> JsString(obj.doctorID.value)),
        "name" -> JsString(obj.name),
        "surname" -> JsString(obj.surname),
        "phoneNumber" -> JsString(obj.phoneNumber),
        "email" -> JsString(obj.email),
        "medicalDegreeGrade" -> JsString(obj.medicalDegreeGrade)
      )
  }

}
