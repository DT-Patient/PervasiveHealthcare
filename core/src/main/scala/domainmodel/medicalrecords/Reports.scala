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

package domainmodel.medicalrecords

import java.time.LocalDateTime

/**
 * Diagnostics.
 *
 * @param value text of diagnostics.
 */
case class Diagnostics(value: String)

/**
 * Consulting.
 *
 * @param value text of consulting.
 */
case class Consulting(value: String)

/**
 * Therapeutic Delivery.
 *
 * @param value text of therapeutic delivery.
 */
case class TherapeuticDelivery(value: String)

/**
 * Rehabilitation.
 *
 * @param value text of rehabilitation.
 */
case class Rehabilitation(value: String)

/**
 * Assistance.
 *
 * @param value text of assistance.
 */
case class Assistance(value: String)

/**
 * Activity made.
 *
 * @param diagnostics         diagnostics of the activity.
 * @param consulting          consulting of the activity.
 * @param therapeuticDelivery therapeutic delivery of the activity.
 * @param rehabilitation      rehabilitation of the activity.
 * @param assistance          assistance of the activity.
 */
case class Activity(
  diagnostics: Diagnostics,
  consulting: Consulting,
  therapeuticDelivery: TherapeuticDelivery,
  rehabilitation: Rehabilitation,
  assistance: Assistance
)

/**
 * Type of treatment.
 *
 * @param value text of treatment type.
 */
case class TreatmentType(value: String)

/**
 * Report of activity that was made.
 *
 * @param activity      activity in report.
 * @param datetime      date and time of report.
 * @param treatmentType type of treatment in report.
 */
case class Report(
  activity: Activity,
  datetime: LocalDateTime = LocalDateTime.now(),
  treatmentType: TreatmentType
)

/**
 * Set of reports.
 */
object Reports {

  case class Reports private (reports: Set[Report] = Set.empty) {

    /**
     * Add new report at the collection.
     *
     * @param report report to add.
     * @return collection of reports.
     */
    def addNewReport(report: Report): Reports = Reports(this.reports + report)
  }

  /**
   * Apply method.
   *
   * @return collection of reports.
   */
  def apply(): Reports = Reports()
}
