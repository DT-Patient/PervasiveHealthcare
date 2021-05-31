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

import domainmodel.professionalfigure.{Anesthetist}
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec._
import org.scalatestplus.junit.JUnitRunner
import java.time.LocalDateTime

import domainmodel.DoctorID

@RunWith(classOf[JUnitRunner])
class AnesthesiologyRecordTest extends AnyFreeSpec {

  val anesthesiologyRecord: AnesthesiologyRecord = AnesthesiologyRecord(
    Anesthetist(
      DoctorID("AJBDIB"),
      "Luigi",
      "Marcelli",
      "4325467389",
      "marcelli@doctor.it",
      "102/110"
    ),
    OperationEvaluation(
      AnestheticCard(LocalDateTime.now(), Description("Total Anesthetic")),
      PostOperationEvaluation(LocalDateTime.now(), Description("All OK"))
    )
  )
  "An anesthesiology record should have" - {
    "an anesthetist" in {
      assert(anesthesiologyRecord.anesthetist != null)
      assert(anesthesiologyRecord.anesthetist.doctorID.value.nonEmpty)
    }
    "a operation evalution " in {
      assert(anesthesiologyRecord.operationEvaluation != null)
    }
  }

  "An operation evaluation  should have" - {
    "an anesthetist card" in {
      assert(anesthesiologyRecord.operationEvaluation.anestheticCard != null)
      assert(
        anesthesiologyRecord.operationEvaluation.anestheticCard.datetime != null
      )
      assert(
        anesthesiologyRecord.operationEvaluation.anestheticCard.description.value.nonEmpty
      )
    }
    "a post operation evalution " in {
      assert(
        anesthesiologyRecord.operationEvaluation.postOperationEvaluation != null
      )
      assert(
        anesthesiologyRecord.operationEvaluation.postOperationEvaluation.datetime != null
      )
      assert(
        anesthesiologyRecord.operationEvaluation.postOperationEvaluation.description.value.nonEmpty
      )

    }
  }
}
