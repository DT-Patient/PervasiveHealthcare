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

import domainmodel.CardiologyVisitHistory.CardiologyVisitHistory
import domainmodel._
import json.EnumerationJsonFormat.EnumJsonConverter
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.RequestJsonFormats.{
  BooleanJsonFormat,
  DoubleJsonFormat,
  IntJsonFormat,
  StringJsonFormat,
  immSetFormat,
  jsonFormat1,
  jsonFormat15
}
import json.generalpractitionerinfo.VisitJsonFormat.visitDateJsonFormat
import spray.json.RootJsonFormat

/**
 * Json format for the cardiology visit.
 */
object CardiologyVisitJsonFormat {

  /**
   * Implicit for the chest pain type enumeration.
   */
  implicit val chestPainTypeJsonFormat: EnumJsonConverter[ChestPainType.type] =
    new EnumJsonConverter(ChestPainType)

  /**
   * Implicit for the resting electrocardiographic enumeration.
   */
  implicit val restingElectrocardiographicJsonFormat
    : EnumJsonConverter[RestingElectrocardiographic.type] =
    new EnumJsonConverter(RestingElectrocardiographic)

  /**
   * Implicit for the slope ST  enumeration.
   */
  implicit val slopeSTJsonFormat: EnumJsonConverter[SlopeST.type] =
    new EnumJsonConverter(SlopeST)

  /**
   * Implicit for the thal enumeration.
   */
  implicit val thalJsonFormat: EnumJsonConverter[Thal.type] =
    new EnumJsonConverter(Thal)

  /**
   * Implicit for the resting blood pressure object.
   */
  implicit val restingBloodPressureJsonFormat
    : RootJsonFormat[RestingBloodPressure] = jsonFormat1(RestingBloodPressure)

  /**
   * Implicit for the cholesterol object.
   */
  implicit val cholesterolJsonFormat: RootJsonFormat[Cholesterol] = jsonFormat1(
    Cholesterol
  )

  /**
   * Implicit for the fasting blood sugar object.
   */
  implicit val fastingBloodSugarJsonFormat: RootJsonFormat[FastingBloodSugar] =
    jsonFormat1(FastingBloodSugar)

  /**
   * Implicit for the max heart rate object.
   */
  implicit val maxHeartRateJsonFormat: RootJsonFormat[MaxHeartRate] =
    jsonFormat1(MaxHeartRate)

  /**
   * Implicit for the old peak ST object.
   */
  implicit val oldPeakSTJsonFormat: RootJsonFormat[OldPeakST] = jsonFormat1(
    OldPeakST
  )

  /**
   * Implicit for the number of vessel coloured object.
   */
  implicit val numberVesselColouredJsonFormat
    : RootJsonFormat[NumberVesselColoured] = jsonFormat1(NumberVesselColoured)

  /**
   * Implicit for the cardiology visit id object.
   */
  implicit val cardiologyVisitIDJsonFormat: RootJsonFormat[CardiologyVisitID] =
    jsonFormat1(CardiologyVisitID)

  /**
   * Implicit for the cardiology visit object.
   */
  implicit val cardiologyVisitJsonFormat: RootJsonFormat[CardiologyVisit] =
    jsonFormat15(CardiologyVisit)

  /**
   * Implicit for the cardiology visit history object.
   */
  implicit val cardiologyVisitHistoryJsonFormat
    : RootJsonFormat[CardiologyVisitHistory] = jsonFormat1(
    CardiologyVisitHistory
  )

}
