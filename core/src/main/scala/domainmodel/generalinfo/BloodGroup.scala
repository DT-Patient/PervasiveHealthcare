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

package domainmodel.generalinfo

/**
 * Different blood types.
 */
object BloodType extends Enumeration {
  type BloodType = Value
  val A, B, AB, ZERO = Value
}

/**
 * Different RH bloods.
 */
object Rh extends Enumeration {
  type Rh = Value
  val POSITIVE, NEGATIVE = Value
}

import domainmodel.generalinfo.BloodType.BloodType
import domainmodel.generalinfo.Rh.Rh

/**
 * Class that models the blood group.
 *
 * @param bloodType the type of the blood.
 * @param rh        the RH of the blood.
 */
case class BloodGroup(bloodType: BloodType, rh: Rh)
