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

import domainmodel.PathologySeverity.PathologySeverity
import domainmodel.PreviousPathologies.PreviousPathologies
import domainmodel.{DetectionDate, Pathology, PathologyName, PathologySeverityLevels}
import json.EnumerationJsonFormat.EnumJsonConverter
import json.LocalDateJsonFormat.DateFormat
import spray.json.DefaultJsonProtocol.{StringJsonFormat, immSetFormat, jsonFormat1, jsonFormat2, jsonFormat3}
import spray.json.RootJsonFormat

object PathologyJsonFormat {
  implicit val pathologySeverityLevelJsonFormat: EnumJsonConverter[PathologySeverityLevels.type] = new EnumJsonConverter(PathologySeverityLevels)
  implicit val pathologySeverityJsonFormat: RootJsonFormat[PathologySeverity] = jsonFormat2(PathologySeverity)
  implicit val detectionDateJsonFormat: RootJsonFormat[DetectionDate] = jsonFormat1(DetectionDate)
  implicit val pathologyNameJsonFormat: RootJsonFormat[PathologyName] = jsonFormat1(PathologyName)
  implicit val pathologyJsonFormat: RootJsonFormat[Pathology] = jsonFormat3(Pathology)
  implicit val previousPathologiesJsonFormat: RootJsonFormat[PreviousPathologies] = jsonFormat1(PreviousPathologies)


}
