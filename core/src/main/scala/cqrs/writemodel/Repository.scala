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

package cqrs.writemodel

object Repository {

  /**
   * Auth value.
   */
  val auth: Auth = new Auth()

  /**
   * Admin CRUD value.
   */
  val adminRepository: AdminCRUD = new AdminCRUD()

  /**
   * Surgeon CRUD value.
   */
  val surgeonRepository: SurgeonCRUD = new SurgeonCRUD()

  /**
   * Rescuer CRUD value.
   */
  val rescuerRepository: RescuerCRUD = new RescuerCRUD()

  /**
   * WardNurse CRUD value.
   */
  val wardNurseRepository: WardNurseCRUD = new WardNurseCRUD()

  /**
   * GeneralPractitioner CRUD value.
   */
  val generalPractitionerRepository: GeneralPractitionerCRUD =
    new GeneralPractitionerCRUD()

  /**
   * Instrumentalist CRUD value.
   */
  val instrumentalistRepository: InstrumentalistCRUD = new InstrumentalistCRUD()

  /**
   * Anesthetist CRUD value.
   */
  val anesthetistRepository: AnesthetistCRUD = new AnesthetistCRUD()

  /**
   * Cardiologist CRUD value.
   */
  val cardiologyRepository: CardiologistCRUD = new CardiologistCRUD()

  /**
   * Patient CRUD value.
   */
  val patientRepository: PatientCRUD = new PatientCRUD()

}
