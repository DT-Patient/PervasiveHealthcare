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

package client.generalpractitioner

import akka.actor.{Actor, ActorLogging, ClassicActorSystemProvider}
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.{Http, HttpExt}
import akka.pattern.pipe
import akka.util.ByteString
import client.Client
import client.generalpractitioner.Message._
import client.generalpractitioner.Requests._
import domainmodel.{CardiologyPrediction, DoctorID}
import json.CardiologyPredictionJsonFormat.cardiologyPredictionJsonFormat
import spray.json.JsonParser

import scala.concurrent.ExecutionContextExecutor

class GeneralPractitionerActor(doctorID: DoctorID, token: String) extends Actor with ActorLogging {
  val http: HttpExt = Http(Client.system)
  implicit val system: ClassicActorSystemProvider = Client.system
  implicit val executionContext: ExecutionContextExecutor = Client.system.classicSystem.dispatcher

  private lazy val onInteractionBehaviour: Receive = {
    case GetGeneralPractitionerInfoMessage(doctorID) =>
      getGeneralPractitionerInfoRequest(doctorID, token).pipeTo(self)
      this.context become onAttendResponseGeneralPractitionerInfoMessageBehaviour
    case InsertGeneralPractitionerInfoMessage(generalPractitionerInfo) =>
      insertGeneralPractitionerInfoRequest(token, generalPractitionerInfo).pipeTo(self)
      this.context become onAttendResponseInsertGeneralPractitionerInfoMessageBehaviour
    case UpdateGeneralPractitionerInfoMessage(generalPractitionerInfo) =>
      updateGeneralPractitionerInfoRequest(token, generalPractitionerInfo.patientID, generalPractitionerInfo).pipeTo(self)
      this.context become onAttendResponseUpdateGeneralPractitionerInfoMessageBehaviour
    case UpdateCardiologyPredictionMessage() =>
      updateCardiologyPredictionsRequest(token, doctorID).pipeTo(self)
    case GetCardiologyPredictionMessage() =>
      getCardiologyPredictionsRequest(token).pipeTo(self)
      this.context become onAttendResponseCardiologyPredictionMessageBehaviour

  }

  private lazy val onAttendResponseGeneralPractitionerInfoMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(body.utf8String)
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, _, _) =>
      println("Error: " + code.value)
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }

  private lazy val onAttendResponseInsertGeneralPractitionerInfoMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.Created, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(body.utf8String)
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, _, _) =>
      println("Error: " + code.value)
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }

  private lazy val onAttendResponseUpdateGeneralPractitionerInfoMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(body.utf8String)
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, _, _) =>
      println("Error: " + code.value)
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }

  private lazy val onAttendResponseCardiologyPredictionMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        val cardiologyPrediction: CardiologyPrediction = JsonParser(body.utf8String).convertTo[CardiologyPrediction]
        println(cardiologyPrediction.toString)
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
