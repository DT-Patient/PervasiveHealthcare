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
package json.medicalrecords

import domainmodel.medicalrecords.MedicalSurgicalDevice
import domainmodel.medicalrecords.MedicalSurgicalDevices.MedicalSurgicalDevices
import json.RequestJsonFormats.immSetFormat
import spray.json.DefaultJsonProtocol.{
  StringJsonFormat,
  jsonFormat1,
  jsonFormat2
}
import spray.json.RootJsonFormat

/**
 * Json format for medical surgical devices object.
 */
object MedicalSurgicalDevicesJsonFormat {

  /**
   * Implicit for medical surgical device object.
   */
  implicit val medicalSurgicalDeviceJsonFormat
    : RootJsonFormat[MedicalSurgicalDevice] = jsonFormat2(MedicalSurgicalDevice)

  /**
   * Implicit for medical surgical devices object.
   */
  implicit val medicalSurgicalDevicesJsonFormat
    : RootJsonFormat[MedicalSurgicalDevices] = jsonFormat1(
    MedicalSurgicalDevices
  )
}
