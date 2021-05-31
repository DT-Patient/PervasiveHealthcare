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

case class VisitDate(visitDate: LocalDate = LocalDate.now())

/**
 * This class represents the visit.
 *
 * @param visitDate , date of the visit.
 */
case class Visit(visitDate: VisitDate)

/**
 * Factory to add a new visit to the visits's history.
 */
object VisitHistory {

  case class VisitHistory private (history: Set[Visit] = Set.empty) {

    def addNewVisit(visit: Visit): VisitHistory =
      VisitHistory(this.history + visit)
  }

  def apply(): VisitHistory = VisitHistory()
}
