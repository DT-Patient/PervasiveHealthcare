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

/**
 * Medical surgical device.
 *
 * @param name  name of medical surgical device.
 * @param label label of medical surgical device.
 */
case class MedicalSurgicalDevice(name: String, label: String)

/**
 * Collection of medical surgical device.
 */
object MedicalSurgicalDevices {

  case class MedicalSurgicalDevices private (
    medicalSurgicalDevices: Set[MedicalSurgicalDevice] = Set.empty
  ) {

    /**
     * Add new medical surgical device.
     *
     * @param medicalSurgicalDevice
     * @return collection of medical surgical device.
     */
    def addNewMedicalSurgicalDevice(
      medicalSurgicalDevice: MedicalSurgicalDevice
    ): MedicalSurgicalDevices =
      MedicalSurgicalDevices(
        this.medicalSurgicalDevices + medicalSurgicalDevice
      )
  }

  /**
   * Apply method.
   *
   * @return collection of medical surgical device.
   */
  def apply(): MedicalSurgicalDevices = MedicalSurgicalDevices()
}
