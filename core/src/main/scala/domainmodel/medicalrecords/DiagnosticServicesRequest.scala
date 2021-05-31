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
import domainmodel.utility.Description

/**
 * Diagnostic Services Request's ID.
 *
 * @param id id of diagnostic services request.
 */
case class DiagnosticServicesRequestID(id: String)

/**
 * Form to fill.
 *
 * @param value text of form.
 */
case class Form(value: String)

/**
 * Diagnostic services request.
 *
 * @param id          id of diagnostic services request.
 * @param description description of diagnostic services request.
 * @param doctorID      doctor that require of diagnostic services.
 * @param form        form to request.
 */
case class DiagnosticServicesRequest(
  id: DiagnosticServicesRequestID,
  description: Description,
  doctorID: DoctorID,
  form: Form
)

/**
 * Collection of diagnostic services request.
 */
object DiagnosticServicesRequests {

  case class DiagnosticServicesRequests private (
    diagnosticServicesRequests: Set[DiagnosticServicesRequest] = Set.empty
  ) {

    /**
     * Add new diagnostic services request.
     *
     * @param diagnosticServicesRequest diagnostic services request to add.
     * @return collection of diagnostic services request.
     */
    def addNewDiagnosticServicesRequest(
      diagnosticServicesRequest: DiagnosticServicesRequest
    ): DiagnosticServicesRequests =
      DiagnosticServicesRequests(
        this.diagnosticServicesRequests + diagnosticServicesRequest
      )
  }

  /**
   * Apply method.
   *
   * @return collection of diagnostic services request.
   */
  def apply(): DiagnosticServicesRequests = DiagnosticServicesRequests()
}
