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

import java.time.{LocalDate}
import domainmodel.generalpractitionerinfo.BookingVisitHistory.BookingVisitHistory
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BookingVisitHistoryTest extends AnyFreeSpec {
  val visit: Visit = Visit(VisitDate())
  val description: Description = Description("visit for shoulder surgery")
  val bookingDate :LocalDate = LocalDate.of(2021, 10, 2)
  val bookingVisit: BookingVisit = BookingVisit(1, visit, description, bookingDate)
  "A booking visits should have" - {
    "an id" in {
      assert(bookingVisit.bookingId == 1)
    }
    "a visit information" in {
      assert(bookingVisit.visit == visit)
    }
    "a description" in {
      assert(bookingVisit.description == description)
    }
    "a visit date" in {
      assert(bookingVisit.bookingData == bookingDate)
    }
  }

  val bookingVisitHistory: BookingVisitHistory = BookingVisitHistory()

  "Booking visit history" - {
    "should be" - {
      "a set" in {
        assert(bookingVisitHistory.history.isInstanceOf[Set[BookingVisit]])
      }
      "initially empty" in {
        assert(bookingVisitHistory.history.isEmpty)
      }
    }
    "can be updated" in {
      assert(bookingVisitHistory.addNewBookingVisit(bookingVisit).history.nonEmpty)
    }
  }
}