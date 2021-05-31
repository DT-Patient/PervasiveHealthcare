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

package json.generalpractitionerinfo

import domainmodel.generalpractitionerinfo.MedicalCertificateHistory.MedicalCertificateHistory
import domainmodel.generalpractitionerinfo.{
  MedicalCertificate,
  MedicalCertificateID
}
import json.RequestJsonFormats.{
  ByteJsonFormat,
  StringJsonFormat,
  immSetFormat,
  jsonFormat2
}
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.RootJsonFormat

/**
 * Json format for medical certificates object.
 */
object MedicalCertificatesJsonFormat {

  implicit lazy val medicalCertificateIDJsonFormat
    : RootJsonFormat[MedicalCertificateID] = jsonFormat1(MedicalCertificateID)

  /**
   * Implicit for medical certificate object.
   */
  implicit val medicalCertificateJsonFormat
    : RootJsonFormat[MedicalCertificate] = jsonFormat2(MedicalCertificate)

  /**
   * Implicit for medical certificate history object.
   */
  implicit val medicalCertificateHistoryJsonFormat
    : RootJsonFormat[MedicalCertificateHistory] = jsonFormat1(
    MedicalCertificateHistory
  )
}
