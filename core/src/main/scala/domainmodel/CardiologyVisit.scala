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

package domainmodel

/**
 * Chest pain type enumeration.
 */
object ChestPainType extends Enumeration {
  type ChestPainType = Value
  val TYPICAL_ANGINA, ATYPICAL_ANGINA, NON_ANGINA_PAIN, ASYMPTOMATIC = Value
}

/**
 * Resting Blood Pressure.
 *
 * @param value , value of property.
 */
case class RestingBloodPressure(value: Int)

/**
 * Cholesterol.
 *
 * @param value , value of property.
 */
case class Cholesterol(value: Int)

/**
 * Fasting Blood Sugar.
 *
 * @param value , value of property.
 */
case class FastingBloodSugar(value: Boolean)

/**
 * Resting Electrocardiographic enumeration.
 */
object RestingElectrocardiographic extends Enumeration {
  type RestingElectrocardiographic = Value
  val NORMAL, STT_ABNORMALITY, LEFT_VENTRICULAR_HYPERTROPHY = Value
}

/**
 * Max Heart Rate.
 *
 * @param value , value of property.
 */
case class MaxHeartRate(value: Int)

/**
 * Old Peak ST.
 *
 * @param value , value of property.
 */
case class OldPeakST(value: Double)

/**
 * Slope ST enumeration.
 */
object SlopeST extends Enumeration {
  type SlopeST = Value
  val UP_SLOPING, FLAT, DOWN_SLOPING = Value
}

/**
 * Number Vessel Coloured.
 *
 * @param value , value of property.
 */
case class NumberVesselColoured(value: Int)

/**
 * Thal enumeration.
 */
object Thal extends Enumeration {
  type Thal = Value
  val NORMAL, FIXED_DEFECT, REVERSIBLE_DEFECT, OTHER = Value
}

import domainmodel.ChestPainType.ChestPainType
import domainmodel.RestingElectrocardiographic.RestingElectrocardiographic
import domainmodel.SlopeST.SlopeST
import domainmodel.Thal.Thal
import domainmodel.generalpractitionerinfo.VisitDate

/**
 * Cardiology Visit ID.
 *
 * @param value , value of property.
 */
case class CardiologyVisitID(value: String)

/**
 * Cardiology Visit.
 *
 * @param patientID                   , id of patient.
 * @param doctorID                    , id of doctor.
 * @param cardiologyVisitID           , id of cardiology visit.
 * @param chestPainType               , chest pain type.
 * @param restingBloodPressure        , resting blood pressure.
 * @param cholesterol                 , cholesterol.
 * @param fastingBloodSugar           , fasting blood sugar.
 * @param restingElectrocardiographic , resting electrocardiographic.
 * @param maxHeartRate                , max heart rate.
 * @param isAnginaInducted            , is angina inducted.
 * @param oldPeakST                   , old peak ST.
 * @param slopeST                     , slope ST.
 * @param numberVesselColoured        , number vessel coloured.
 * @param thal                        , thal.
 * @param visitDate                   , visit date.
 */
case class CardiologyVisit(
  patientID: PatientID,
  doctorID: DoctorID,
  cardiologyVisitID: CardiologyVisitID,
  chestPainType: ChestPainType,
  restingBloodPressure: RestingBloodPressure,
  cholesterol: Cholesterol,
  fastingBloodSugar: FastingBloodSugar,
  restingElectrocardiographic: RestingElectrocardiographic,
  maxHeartRate: MaxHeartRate,
  isAnginaInducted: Boolean,
  oldPeakST: OldPeakST,
  slopeST: SlopeST,
  numberVesselColoured: NumberVesselColoured,
  thal: Thal,
  visitDate: VisitDate
)

/**
 * Cardiology Visit History.
 */
object CardiologyVisitHistory {

  case class CardiologyVisitHistory private (
    history: Set[CardiologyVisit] = Set.empty
  ) {

    def addNewVisit(visit: CardiologyVisit): CardiologyVisitHistory =
      CardiologyVisitHistory(this.history + visit)
  }

  def apply(): CardiologyVisitHistory = CardiologyVisitHistory()
}
