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

package client.generalpractitioner

import client.Message
import domainmodel.DoctorID
import domainmodel.generalpractitionerinfo.GeneralPractitionerInfo

object Message {

  /**
   * Insert General Practitioner Info Message.
   *
   * @param generalPractitionerInfo , general practitioner info.
   */
  case class InsertGeneralPractitionerInfoMessage(
    generalPractitionerInfo: GeneralPractitionerInfo
  ) extends Message

  /**
   * Update General Practitioner Info Message.
   *
   * @param generalPractitionerInfo , general practitioner info.
   */
  case class UpdateGeneralPractitionerInfoMessage(
    generalPractitionerInfo: GeneralPractitionerInfo
  ) extends Message

  /**
   * Get General Practitioner Info Message.
   *
   * @param doctorID , id of doctor.
   */
  case class GetGeneralPractitionerInfoMessage(doctorID: DoctorID)
    extends Message

  /**
   * Update Cardiology Prediction Message.
   */
  case class UpdateCardiologyPredictionMessage() extends Message

  /**
   * Get Cardiology Prediction Message.
   */
  case class GetCardiologyPredictionMessage() extends Message

}
