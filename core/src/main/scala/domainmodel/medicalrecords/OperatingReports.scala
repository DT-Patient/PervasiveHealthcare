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

import domainmodel.medicalrecords.Medical._
import domainmodel.professionalfigure.{Anesthetist, Instrumentalist, Surgeon}
import domainmodel.utility.Note

import java.time.LocalDateTime

/**
 * Surgeon Report.
 *
 * @param surgeon , surgeon.
 * @param note    , note.
 */
case class SurgeonReport(surgeon: Surgeon, note: Option[Note])

/**
 * Anesthetist Report.
 *
 * @param anesthetist , anesthetist.
 * @param note        , note.
 */
case class AnesthetistReport(anesthetist: Anesthetist, note: Option[Note])

/**
 * Instrumentalist Report.
 *
 * @param instrumentalist , instrumentalist.
 * @param note            , note.
 */
case class InstrumentalistReport(
  instrumentalist: Instrumentalist,
  note: Option[Note]
)

/**
 * Medical.
 */
object Medical {

  case class Medical private (
    surgeons: Set[SurgeonReport] = Set.empty,
    anesthetists: Set[AnesthetistReport] = Set.empty,
    instrumentalists: Set[InstrumentalistReport] = Set.empty
  ) {

    def addNewDoctor(surgeon: SurgeonReport): Medical =
      Medical(this.surgeons + surgeon, anesthetists, instrumentalists)

    def addNewAnesthetist(anesthetist: AnesthetistReport): Medical =
      Medical(surgeons, this.anesthetists + anesthetist, instrumentalists)

    def addNewInstrumentalist(instrumentalist: InstrumentalistReport): Medical =
      Medical(surgeons, anesthetists, this.instrumentalists + instrumentalist)

  }

  /**
   * Apply method.
   *
   * @return collection of single sheet therapy.
   */
  def apply(): Medical = Medical()
}

/**
 * Intervention.
 *
 * @param value , value of intervention.
 */
case class InterventionType(value: String)

/**
 * Operating Reports.
 *
 * @param medical          , medical.
 * @param datetime         , date and time.
 * @param interventionType , intervention type.
 */
case class OperatingReports(
  medical: Medical,
  datetime: LocalDateTime = LocalDateTime.now(),
  interventionType: InterventionType
)
