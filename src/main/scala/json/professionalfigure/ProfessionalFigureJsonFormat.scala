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

import cqrs.writemodel.Role
import domainmodel.professionalfigure._
import json.EnumerationJsonFormat.EnumJsonConverter
import spray.json.DefaultJsonProtocol._
import spray.json.{DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

/**
 * Json format for professional figure object.
 */
object ProfessionalFigureJsonFormat {

  /**
   * Implicit for doctor ID object.
   */
  implicit val doctorIDJsonFormat: RootJsonFormat[DoctorID] = jsonFormat1(DoctorID)

  /**
   * Implicit for specialization object.
   */
  implicit val specializationJsonFormat: EnumJsonConverter[Specialization.type] = new EnumJsonConverter(Specialization)

  /**
   * Implicit for surgeon object.
   */
  implicit object SurgeonJsonFormat extends RootJsonFormat[Surgeon] {
    override def read(json: JsValue): Surgeon = {
      json.asJsObject.getFields(
        "doctorID", "name", "surname", "phoneNumber", "email", "medicalDegreeGrade", "specialization", "role") match {
        case Seq(doctorID, JsString(name), JsString(surname), JsString(phoneNumber), JsString(email), JsString(medicalDegreeGrade),
        JsNumber(specialization), JsNumber(_)) =>
          Surgeon(doctorID.convertTo[DoctorID], name, surname, phoneNumber,
            email, medicalDegreeGrade, Specialization(specialization.toInt))
        case _ => throw DeserializationException("Surgeon expected")
      }
    }

    override def write(obj: Surgeon): JsValue = JsObject(
      "doctorID" -> JsObject("value" -> JsString(obj.doctorID.value)),
      "name" -> JsString(obj.name),
      "surname" -> JsString(obj.surname),
      "phoneNumber" -> JsString(obj.phoneNumber),
      "email" -> JsString(obj.email),
      "medicalDegreeGrade" -> JsString(obj.medicalDegreeGrade),
      "specialization" -> JsNumber(obj.specialization.id),
      "role" -> JsNumber(Role.SURGEON.id)
    )
  }

  /**
   * Implicit for anesthetist object.
   */
  implicit object AnesthetistJsonFormat extends RootJsonFormat[Anesthetist] {
    override def read(json: JsValue): Anesthetist = {
      json.asJsObject.getFields(
        "doctorID", "name", "surname", "phoneNumber", "email", "medicalDegreeGrade", "role") match {
        case Seq(doctorID, JsString(name), JsString(surname), JsString(phoneNumber),
        JsString(email), JsString(medicalDegreeGrade), JsNumber(_)) =>
          Anesthetist(doctorID.convertTo[DoctorID], name, surname, phoneNumber, email, medicalDegreeGrade)
        case _ => throw DeserializationException("Anesthetist expected")
      }
    }

    override def write(obj: Anesthetist): JsValue = JsObject(
      "doctorID" -> JsObject("value" -> JsString(obj.doctorID.value)),
      "name" -> JsString(obj.name),
      "surname" -> JsString(obj.surname),
      "phoneNumber" -> JsString(obj.phoneNumber),
      "email" -> JsString(obj.email),
      "role" -> JsNumber(Role.ANESTHETIST.id)
    )
  }

  /**
   * Implicit for instrumentalist object.
   */
  implicit object InstrumentalistJsonFormat extends RootJsonFormat[Instrumentalist] {
    override def read(json: JsValue): Instrumentalist = {
      json.asJsObject.getFields(
        "doctorID", "name", "surname", "phoneNumber", "email", "nursingDegreeGrade", "role") match {
        case Seq(doctorID, JsString(name), JsString(surname), JsString(phoneNumber),
        JsString(email), JsString(nursingDegreeGrade), JsNumber(_)) =>
          Instrumentalist(doctorID.convertTo[DoctorID], name, surname, phoneNumber, email, nursingDegreeGrade)
        case _ => throw DeserializationException("Anesthetist expected")
      }
    }

    override def write(obj: Instrumentalist): JsValue = JsObject(
      "doctorID" -> JsObject("value" -> JsString(obj.doctorID.value)),
      "name" -> JsString(obj.name),
      "surname" -> JsString(obj.surname),
      "phoneNumber" -> JsString(obj.phoneNumber),
      "email" -> JsString(obj.email),
      "role" -> JsNumber(Role.INSTRUMENTALIST.id)
    )
  }

  //TODO other professional figures
  /**
   * Implicit for surgeons object.
   */
  implicit val surgeonsJsonFormat: RootJsonFormat[Surgeons] = jsonFormat1(Surgeons)
}
