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

package domainmodel

import domainmodel.Familiars._
import domainmodel.KinshipDegree.KinshipDegree
import domainmodel.PreviousPathologies._
import domainmodel.Remotes._

import java.time.LocalDate

/**
 * Kinship Degree.
 */
object KinshipDegree extends Enumeration {
  type KinshipDegree = Value
  val MOTHER, FATHER, LEGAL_TUTOR = Value
}

/**
 * Familiar.
 *
 * @param name                , name.
 * @param kinshipDegree       , kinship degree.
 * @param previousPathologies , previous pathologies.
 * @param phoneNumber         , phone number.
 */
case class Familiar(
  name: String,
  kinshipDegree: KinshipDegree,
  previousPathologies: PreviousPathologies,
  phoneNumber: String
)

/**
 * Remote.
 *
 * @param info , information.
 * @param date , date.
 */
case class Remote(info: String, date: LocalDate = java.time.LocalDate.now)

/**
 * Physiologic.
 *
 * @param info , information.
 * @param date , date.
 */
case class Physiologic(info: String, date: LocalDate = java.time.LocalDate.now)

/**
 * This class represents the anamnesis.
 * There are various steps that make up the anamnesis including family, remote and psychological anamnesis.
 */
case class Anamnesis(
  familiars: Familiars,
  remotes: Remotes,
  physiologic: Physiologic
)

/**
 * Factory to add a new remote anamnesis to the anamnesis's history.
 */
object Remotes {

  case class Remotes private (history: Set[Remote] = Set.empty) {
    def addNewRemote(remote: Remote): Remotes = Remotes(this.history + remote)
  }

  def apply(): Remotes = Remotes()
}

/**
 * Factory to add a new familiar anamnesis to the anamnesis's history.
 */
object Familiars {

  case class Familiars private (familiars: Set[Familiar] = Set.empty) {

    def addNewFamiliar(familiar: Familiar): Familiars =
      Familiars(this.familiars + familiar)
  }

  def apply(): Familiars = Familiars()
}
