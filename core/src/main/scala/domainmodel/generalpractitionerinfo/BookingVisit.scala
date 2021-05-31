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

import domainmodel.utility.Description

import java.time.LocalDate

/**
 * This class represents the history of the visits that make up the patient's clinical history.
 *
 * @param bookingId   , booking id
 * @param visit       , visit's information
 * @param description , visit's description
 * @param bookingData , booking's date.
 */
case class BookingVisit(
  bookingId: Int,
  visit: Visit,
  description: Description,
  bookingData: LocalDate
)

/**
 * The set of booking visits of the patient.
 */
object BookingVisitHistory {

  /**
   * Class that models the history of a patient's booking visits.
   *
   * @param history the set of booking visits.
   */
  case class BookingVisitHistory private (
    history: Set[BookingVisit] = Set.empty
  ) {

    /**
     * Add a new booking visit to the history.
     *
     * @param bookingVisit the booking visit to add.
     * @return the updated history.
     */
    def addNewBookingVisit(bookingVisit: BookingVisit): BookingVisitHistory =
      BookingVisitHistory(this.history + bookingVisit)
  }

  /**
   * Factory method per PrescriptionHistory.
   *
   * @return an empty set of booking visits.
   */
  def apply(): BookingVisitHistory = BookingVisitHistory()

}
