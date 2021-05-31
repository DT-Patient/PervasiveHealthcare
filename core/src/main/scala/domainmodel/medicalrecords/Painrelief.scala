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

import domainmodel.utility.Description

import java.time.LocalDateTime

/**
 * Pain reliever.
 *
 * @param datetime    date and time of administration.
 * @param description description of administration.
 */
case class Painrelief(
  datetime: LocalDateTime = LocalDateTime.now(),
  description: Description
)

object PainreliefHistory {

  case class PainreliefHistory private (
    painreliefs: Set[Painrelief] = Set.empty
  ) {

    /**
     * Add new painreief .
     *
     * @param painrelief painrelief to add.
     * @return collection of painreliefs.
     */
    def addNewPainrelief(painrelief: Painrelief): PainreliefHistory =
      PainreliefHistory(this.painreliefs + painrelief)

  }

  /**
   * Apply method.
   *
   * @return collection of painreiefs.
   */
  def apply(): PainreliefHistory = PainreliefHistory()
}
