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
 * Different kinds of allergies.
 */
object AllergyClass extends Enumeration {
  type AllergyClass = Value

  val RESPIRATORY_ALLERGY, FOOD_ALLERGY, DRUG_ALLERGY,
    HYMENOPTERA_VENOM_ALLERGY, ATOPIC_DERMATITIS, ALLERGIC_CONTACT_DERMATITIS,
    FOOD_INTOLERANCE, CELIAC_DISEASE = Value
}

import domainmodel.generalinfo.AllergyClass.AllergyClass

/**
 * A brief allergy description.
 *
 * @param value the allergy description.
 */
case class AllergyDescription(value: String)

/**
 * Class that models an allergy.
 *
 * @param allergyClass the kind of allergy.
 * @param description  a brief allergy description.
 */
case class Allergy(allergyClass: AllergyClass, description: AllergyDescription)

/**
 * Companion object for a set of allergies.
 */
object Allergies {

  /**
   * Class that models a set o allergies.
   *
   * @param allergies the set of allergies.
   */
  case class Allergies private (allergies: Set[Allergy] = Set.empty) {

    /**
     * Add new allergy in the set of allergies.
     *
     * @param allergy the allergy to add.
     * @return the updated set of allergies.
     */
    def addNewAllergy(allergy: Allergy): Allergies =
      Allergies(this.allergies + allergy)
  }

  /**
   * Factory method for Allergies.
   *
   * @return an empty set of allergies.
   */
  def apply(): Allergies = Allergies()
}
