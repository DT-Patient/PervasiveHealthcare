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

import domainmodel.medicalrecords.VitalSigns._
import domainmodel.utility.Info

import java.time.LocalDateTime

/**
 * Vital sign.
 *
 * @param info     information of vital sign.
 * @param datetime date of registration of vital sign.
 */
case class VitalSign(info: Info, datetime: LocalDateTime = LocalDateTime.now())

/**
 * Collection of vital signs.
 */
object VitalSigns {

  case class VitalSigns private (vitalSigns: Set[VitalSign] = Set.empty) {

    /**
     * Method to add new vital sign at the collection.
     *
     * @param vitalSign vital sign to add.
     * @return collection of vital sign.
     */
    def addNewVitalSign(vitalSign: VitalSign): VitalSigns =
      VitalSigns(this.vitalSigns + vitalSign)
  }

  /**
   * Apply method.
   *
   * @return collection of vital sign.
   */
  def apply(): VitalSigns = VitalSigns()
}

/**
 * Graphics of vital signs.
 *
 * @param vitalSigns vital signs in graphics.
 */
case class Graphic(vitalSigns: VitalSigns)
