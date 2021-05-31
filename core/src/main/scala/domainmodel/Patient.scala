/*
 * Copyright (c) 2021.  Ylenia Battistini, Enrico Gnagnarella, Matteo Scucchia
 *
 *                              Licensed under the Apache License, Version 2.0 (the "License");
 *                              you may not use this file except in compliance with the License.
 *                              You may obtain a copy of the License at
 *
 *                                  http://www.apache.org/licenses/LICENSE-2.0
 *
 *                              Unless required by applicable law or agreed to in writing, software
 *                              distributed under the License is distributed on an "AS IS" BASIS,
 *                              WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *                              See the License for the specific language governing permissions and
 *                              limitations under the License.
 */

package domainmodel

import domainmodel.CardiologyVisitHistory._
import domainmodel.Gender.Gender
import domainmodel.generalinfo.GeneralInfo
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo
import domainmodel.medicalrecords.MedicalRecord
import domainmodel.medicalrecords.MedicalRecordHistory.MedicalRecordHistory

import java.time.LocalDate
import scala.util.matching.Regex

object Gender extends Enumeration {
  type Gender = Value
  val FEMALE, MALE, OTHER = Value
}

object Patient {

  /**
   * Patient constructor.
   *
   * @param patientID               , id of patient.
   * @param cf                      , fiscal code.
   * @param name                    , name.
   * @param surname                 , surname.
   * @param birthDate               , birthday date.
   * @param birthplace              , birthday place.
   * @param gender                  , gender.
   * @param phone                   , phone.
   * @param mobilePhone             , mobil phone.
   * @param address                 , address.
   * @param residenceAddress        , residence address.
   * @param residenceCity           , residence city.
   * @param province                , province.
   * @param generalInfo             , general info of patient.
   * @param generalPractitionerInfo , general practitioner info of patient.
   * @param medicalRecords          , medical records of patient.
   * @param cardiologyVisitHistory  , cardiology visits of patient.
   */
  case class Patient(
    patientID: PatientID,
    cf: CF,
    name: String,
    surname: String,
    birthDate: LocalDate,
    birthplace: String,
    gender: Gender,
    phone: String,
    mobilePhone: Option[String],
    address: String,
    residenceAddress: String,
    residenceCity: String,
    province: String,
    generalInfo: Option[GeneralInfo],
    generalPractitionerInfo: Option[GeneralPractitionerInfo],
    medicalRecords: Option[MedicalRecordHistory],
    cardiologyVisitHistory: Option[CardiologyVisitHistory]
  )

  /**
   * Update medical records.
   *
   * @param patient       , patient to update.
   * @param medicalRecord , new medical record.
   * @return patient updated.
   */
  def updateMedicalRecords(
    patient: Patient,
    medicalRecord: MedicalRecord
  ): Patient = {
    Patient(
      patient.patientID,
      patient.cf,
      patient.name,
      patient.surname,
      patient.birthDate,
      patient.birthplace,
      patient.gender,
      patient.phone,
      patient.mobilePhone,
      patient.address,
      patient.residenceAddress,
      patient.residenceCity,
      patient.province,
      patient.generalInfo,
      patient.generalPractitionerInfo,
      if (patient.medicalRecords.nonEmpty)
        Some(patient.medicalRecords.get.addNewMedicalRecord(medicalRecord))
      else Some(MedicalRecordHistory().addNewMedicalRecord(medicalRecord)),
      patient.cardiologyVisitHistory
    )
  }

  /**
   * Update general info.
   *
   * @param patient     , patient to update.
   * @param generalInfo , new general info.
   * @return patient updated.
   */
  def updateGeneralInfo(patient: Patient, generalInfo: GeneralInfo): Patient = {
    Patient(
      patient.patientID,
      patient.cf,
      patient.name,
      patient.surname,
      patient.birthDate,
      patient.birthplace,
      patient.gender,
      patient.phone,
      patient.mobilePhone,
      patient.address,
      patient.residenceAddress,
      patient.residenceCity,
      patient.province,
      Some(generalInfo),
      patient.generalPractitionerInfo,
      patient.medicalRecords,
      patient.cardiologyVisitHistory
    )
  }

  /**
   * Update general practitioner info.
   *
   * @param patient                 , patient to update.
   * @param generalPractitionerInfo , new general practitioner info.
   * @return patient updated.
   */
  def updateGeneralPractitionerInfo(
    patient: Patient,
    generalPractitionerInfo: GeneralPractitionerInfo
  ): Patient = {
    Patient(
      patient.patientID,
      patient.cf,
      patient.name,
      patient.surname,
      patient.birthDate,
      patient.birthplace,
      patient.gender,
      patient.phone,
      patient.mobilePhone,
      patient.address,
      patient.residenceAddress,
      patient.residenceCity,
      patient.province,
      patient.generalInfo,
      Some(generalPractitionerInfo),
      patient.medicalRecords,
      patient.cardiologyVisitHistory
    )
  }

  /**
   * Update cardiology visit.
   *
   * @param patient         , patient to update.
   * @param cardiologyVisit , new cardiology visit.
   * @return patient updated.
   */
  def updateCardiologyVisit(
    patient: Patient,
    cardiologyVisit: CardiologyVisit
  ): Patient = {
    Patient(
      patient.patientID,
      patient.cf,
      patient.name,
      patient.surname,
      patient.birthDate,
      patient.birthplace,
      patient.gender,
      patient.phone,
      patient.mobilePhone,
      patient.address,
      patient.residenceAddress,
      patient.residenceCity,
      patient.province,
      patient.generalInfo,
      patient.generalPractitionerInfo,
      patient.medicalRecords,
      if (patient.cardiologyVisitHistory.nonEmpty)
        Some(patient.cardiologyVisitHistory.get.addNewVisit(cardiologyVisit))
      else Some(CardiologyVisitHistory().addNewVisit(cardiologyVisit))
    )

  }

}

case class CF(value: String) {

  /**
   * Verify correctness of CF.
   *
   * @return
   */
  def verifyCorrectness: Boolean = {
    value matches ("""/^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:
        |[04LQ][1-9MNP-V]|[15MR][\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|
        |[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ]
        |[1-9MNP-V]|[\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i""".stripMargin)
  }
}

object RegexUtils {

  /**
   * Verify correctness of regex.
   *
   * @param underlying , regex to verify.
   */
  implicit class RichRegex(val underlying: Regex) extends AnyVal {
    def matches(s: String): Boolean = underlying.pattern.matcher(s).matches
  }

}
