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

package client.patient

import akka.actor.{Actor, ActorLogging, ClassicActorSystemProvider}
import akka.http.scaladsl.model._
import akka.http.scaladsl.{Http, HttpExt}
import akka.pattern.pipe
import akka.util.ByteString
import client.Client
import client.patient.Message._
import client.patient.Requests._
import domainmodel.Patient._
import domainmodel.PatientID
import json.PatientJsonFormat.PatientJsonFormat
import spray.json.JsonParser

import scala.concurrent.ExecutionContextExecutor


class PatientActor(patientID: PatientID, token: String) extends Actor with ActorLogging {

  val http: HttpExt = Http(Client.system)
  implicit val system: ClassicActorSystemProvider = Client.system
  implicit val executionContext: ExecutionContextExecutor = Client.system.classicSystem.dispatcher


  private lazy val onInteractionBehaviour: Receive = {
    case GetMyInfoMessage() =>
      patientRequest(token, patientID).pipeTo(self)
      this.context become onAttendResponseGetMyInfoMessageBehaviour

  }

  private lazy val onAttendResponseGetMyInfoMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        try {
          val patient: Patient = JsonParser(body.utf8String).convertTo[Patient]
          println(patient)
        }
        catch {
          case e: Exception => println("Error, patient not correct!")
        }
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, _, _) =>
      println("Error: " + code.value)
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }


  override def receive: Receive =
    this.onInteractionBehaviour

}