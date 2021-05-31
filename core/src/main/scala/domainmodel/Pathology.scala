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

import domainmodel.PathologySeverity._

import java.time.LocalDate

/**
 * Different levels of pathology severity.
 */
object PathologySeverityLevels extends Enumeration {
  type PathologySeverityLevels = Value
  val ONE, TWO, THREE, FOUR = Value
}

import domainmodel.PathologySeverityLevels.PathologySeverityLevels

/**
 * Companion object for the pathology severity.
 */
object PathologySeverity {

  /**
   * Class that models the pathology severity.
   *
   * @param description a brief description of the pathology.
   * @param severity    the severity level of the pathology.
   */
  case class PathologySeverity private (
    description: String,
    severity: PathologySeverityLevels
  )

  /**
   * Factory method for domainmodel.PathologySeverity.
   *
   * @param pathologySeverityLevels the level of severity.
   * @return a domainmodel.PathologySeverity.
   */
  def apply(
    pathologySeverityLevels: PathologySeverityLevels
  ): PathologySeverity =
    pathologySeverityLevels match {
      case PathologySeverityLevels.ONE =>
        PathologySeverity(
          description = "Low disease",
          severity = PathologySeverityLevels.ONE
        )
      case PathologySeverityLevels.TWO =>
        PathologySeverity(
          description = "Changes life quality",
          severity = PathologySeverityLevels.TWO
        )
      case PathologySeverityLevels.THREE =>
        PathologySeverity(
          description = "Causes disability",
          severity = PathologySeverityLevels.THREE
        )
      case PathologySeverityLevels.FOUR =>
        PathologySeverity(
          description = "Threatens life",
          severity = PathologySeverityLevels.FOUR
        )
    }
}

/**
 * Class that models the date of detection of a previous pathology.
 *
 * @param value the date of diagnosis.
 */
case class DetectionDate(value: LocalDate = LocalDate.now())

/**
 * Class that models the name of the pathology.
 *
 * @param value the name of the pathology.
 */
case class PathologyName(value: String)

/**
 * Class that models a previous pathology.
 *
 * @param pathologyName     the name of the pathology.
 * @param detectionDate     the date of diagnosis.
 * @param pathologySeverity the severity of the pathology.
 */
case class Pathology(
  pathologyName: PathologyName,
  detectionDate: DetectionDate,
  pathologySeverity: PathologySeverity
)

/**
 * The set of previous pathologies of a patient.
 */
object PreviousPathologies {

  /**
   * Class that models the previous pathologies.
   *
   * @param pathologies the set of previous pathologies.
   */
  case class PreviousPathologies private (
    pathologies: Set[Pathology] = Set.empty
  ) {

    /**
     * Add new pathology to the previous pathologies.
     *
     * @param previousPathology the pathology to add.
     * @return an updated set of previous pathologies.
     */
    def addNewPathology(previousPathology: Pathology): PreviousPathologies =
      PreviousPathologies(this.pathologies + previousPathology)
  }

  /**
   * Apply method for domain model.PreviousPathologies.
   *
   * @return an empty set of previous pathologies.
   */
  def apply(): PreviousPathologies = PreviousPathologies()
}
