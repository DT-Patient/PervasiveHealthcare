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

import domainmodel.DoctorID
import domainmodel.medicalrecords.DiagnosticServicesRequests._
import domainmodel.utility.Description
import org.junit.runner.RunWith
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DiagnosticServicesRequestTest extends AnyFreeSpec {

  val diagnosticServicesRequest: DiagnosticServicesRequest =
    DiagnosticServicesRequest(
      DiagnosticServicesRequestID("ABDYBF@fdiwb"),
      Description("Requset of X-RAY"),
      DoctorID("HBAYBDU"),
      Form("Compiled")
    )
  "A prescription should have" - {
    "an ID" in {
      assert(diagnosticServicesRequest.id != null)
    }
    "a brief description" in {
      assert(diagnosticServicesRequest.description.value.nonEmpty)
    }
    "a doctor ID" in {
      assert(diagnosticServicesRequest.doctorID.value.nonEmpty)
    }
    "a form" in {
      assert(diagnosticServicesRequest.form.value.nonEmpty)
    }
  }

  val diagnosticServicesRequests: DiagnosticServicesRequests =
    DiagnosticServicesRequests()

  "Diagnostic services requests" - {
    "should be" - {
      "a set" in {
        assert(
          diagnosticServicesRequests.diagnosticServicesRequests
            .isInstanceOf[Set[DiagnosticServicesRequest]]
        )
      }
      "initially empty" in {
        assert(diagnosticServicesRequests.diagnosticServicesRequests.isEmpty)
      }
    }
    "can be updated" in {
      assert(
        diagnosticServicesRequests
          .addNewDiagnosticServicesRequest(diagnosticServicesRequest)
          .diagnosticServicesRequests
          .nonEmpty
      )
    }
  }
}
