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

package domainmodel.generalpractitionerinfo

import java.time.LocalDate

case class TherapyDate(therapyDate: LocalDate = LocalDate.now())

case class TherapyDescription(therapyDescription: String)

case class TherapyInitialDate(therapyInitialDate: LocalDate)

case class TherapyFinalDate(therapyFinalDate: LocalDate)

/**
 * This class represents the therapies prescribed by the general practitioner for the patient.
 *
 * @param therapyDate        , date of therapy
 * @param therapyDescription , therapy description
 * @param therapyInitialDate , initial date of the therapy
 * @param therapyFinalDate   , final date of the therapy
 */
case class Therapy(
  therapyDate: TherapyDate,
  therapyDescription: TherapyDescription,
  therapyInitialDate: TherapyInitialDate,
  therapyFinalDate: Option[TherapyFinalDate]
)

/**
 * Factory to add a new remote therapy to the therapies's history.
 */
object TherapyHistory {

  case class TherapyHistory private (history: Set[Therapy] = Set.empty) {

    def addNewTherapy(therapy: Therapy): TherapyHistory =
      TherapyHistory(this.history + therapy)
  }

  def apply(): TherapyHistory = TherapyHistory()
}
