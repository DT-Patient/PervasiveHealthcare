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

import domainmodel.CardiologyPrediction
import json.IDJsonFormat.{doctorIDJsonFormat, patientIDJsonFormat}
import json.CardiologyVisitJsonFormat.cardiologyVisitJsonFormat
import json.RequestJsonFormats.{BooleanJsonFormat, jsonFormat4}
import spray.json.ImplicitDerivedJsonProtocol.implicitJsonFormat
import spray.json.RootJsonFormat

/**
 * Json format for the cardiology prediction.
 */
object CardiologyPredictionJsonFormat {

  /**
   * Implicit for the cardiology prediction object.
   */
  implicit val cardiologyPredictionJsonFormat: RootJsonFormat[CardiologyPrediction] = jsonFormat4(CardiologyPrediction)

}
