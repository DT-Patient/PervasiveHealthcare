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

import java.time.LocalDateTime

/**
 * Request.
 *
 * @param value description of request.
 */
case class Request(value: String)

/**
 * Advice request.
 *
 * @param datetime date and time when the request was made.
 * @param request  the request made.
 */
case class AdviceRequest(
  datetime: LocalDateTime = LocalDateTime.now(),
  request: Request
)
