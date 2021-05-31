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

import cqrs.writemodel.Role
import domainmodel.CardiologyVisitHistory.CardiologyVisitHistory
import domainmodel.Gender.Gender
import domainmodel.Patient.Patient
import domainmodel.generalinfo.GeneralInfo
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.medicalrecords.MedicalRecordHistory.MedicalRecordHistory
import domainmodel.{CF, Gender, PatientID}
import json.CardiologyVisitJsonFormat._
import json.EnumerationJsonFormat.EnumJsonConverter
import json.IDJsonFormat.patientIDJsonFormat
import json.LocalDateJsonFormat.DateFormat
import json.RequestJsonFormats.StringJsonFormat
import json.generalinfo.GeneralInfoJsonFormat.generalInfoJsonFormat
import json.generalpractitionerinfo.GeneralPractitionerInfoJsonFormat.generalPractitionerInfoJsonFormat
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordsJsonFormat
import json.professionalfigure.ProfessionalFigureJsonFormat.roleJsonFormat
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.{
  DeserializationException,
  JsObject,
  JsString,
  JsValue,
  RootJsonFormat,
  enrichAny
}

import java.time.LocalDate

/**
 * Json format for patient object.
 */
object PatientJsonFormat {

  implicit val genderJsonFormat: EnumJsonConverter[Gender.type] =
    new EnumJsonConverter(Gender)

  /**
   * Implicit for surgeon object.
   */

  implicit object PatientJsonFormat extends RootJsonFormat[Patient] {

    override def read(json: JsValue): Patient = {
      json.asJsObject.getFields(
        "patientID",
        "cf",
        "name",
        "surname",
        "birthDate",
        "birthplace",
        "gender",
        "phone",
        "address",
        "residenceAddress",
        "residenceCity",
        "province",
        "generalInfo",
        "generalPractitionerInfo",
        "medicalRecords",
        "cardiologyVisitHistory",
        "role"
      ) match {
        case Seq(
              patientID,
              cf,
              JsString(name),
              JsString(surname),
              birthDate,
              JsString(birthplace),
              gender,
              JsString(phone),
              JsString(address),
              JsString(residenceAddress),
              JsString(residenceCity),
              JsString(province),
              generalInfo,
              generalPractitionerInfo,
              medicalRecords,
              cardiologyVisitHistory,
              JsObject(_)
            ) => {

          Patient(
            patientID.convertTo[PatientID],
            cf.convertTo[CF],
            name,
            surname,
            birthDate.convertTo[LocalDate],
            birthplace,
            gender.convertTo[Gender],
            phone,
            Option(json.asJsObject.fields("mobilePhone")).map(mobilePhone =>
              mobilePhone.convertTo[String]
            ),
            address,
            residenceAddress,
            residenceCity,
            province,
            if (generalInfo.asJsObject.fields.nonEmpty)
              Some(generalInfo.convertTo[GeneralInfo])
            else None,
            if (generalPractitionerInfo.asJsObject.fields.nonEmpty)
              Some(generalPractitionerInfo.convertTo[GeneralPractitionerInfo])
            else None,
            if (medicalRecords.asJsObject.fields.nonEmpty)
              Some(medicalRecords.convertTo[MedicalRecordHistory])
            else None,
            if (cardiologyVisitHistory.asJsObject.fields.nonEmpty)
              Some(cardiologyVisitHistory.convertTo[CardiologyVisitHistory])
            else None
          )
        }
        case _ => throw DeserializationException("Patient expected")
      }
    }

    override def write(obj: Patient): JsValue = {
      var jsObj = JsObject(
        "patientID" -> JsObject("value" -> JsString(obj.patientID.value)),
        "cf" -> JsObject("value" -> JsString(obj.cf.value)),
        "name" -> JsString(obj.name),
        "surname" -> JsString(obj.surname),
        "birthDate" -> JsString(obj.birthDate.toString),
        "birthplace" -> JsString(obj.birthplace),
        "gender" -> obj.gender.toJson,
        "phone" -> JsString(obj.phone),
        "address" -> JsString(obj.address),
        "residenceAddress" -> JsString(obj.residenceAddress),
        "residenceCity" -> JsString(obj.residenceCity),
        "province" -> JsString(obj.province),
        "generalInfo" -> obj.generalInfo.map(_.toJson).getOrElse(JsObject()),
        "generalPractitionerInfo" -> obj.generalPractitionerInfo
          .map(_.toJson)
          .getOrElse(JsObject()),
        "medicalRecords" -> obj.medicalRecords
          .map(_.toJson)
          .getOrElse(JsObject()),
        "cardiologyVisitHistory" -> obj.cardiologyVisitHistory
          .map(_.toJson)
          .getOrElse(JsObject()),
        "role" -> Role.PATIENT.toJson
      )
      if (obj.mobilePhone.nonEmpty) {
        jsObj = JsObject(
          jsObj.fields + ("mobilePhone" -> JsString(obj.mobilePhone.get))
        )
      }
      jsObj
    }
  }

  /**
   * Implicit for CF object.
   */
  implicit val cfJsonFormat: RootJsonFormat[CF] = jsonFormat1(CF)

}
