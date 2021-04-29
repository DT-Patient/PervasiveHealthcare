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

package client.surgeon

import akka.actor.{Actor, ActorLogging, ClassicActorSystemProvider}
import akka.http.scaladsl.model._
import akka.http.scaladsl.{Http, HttpExt}
import akka.pattern.pipe
import akka.util.ByteString
import client.Client
import client.surgeon.SurgeonMessage._
import client.surgeon.Requests._
import domainmodel.DoctorID
import domainmodel.medicalrecords.MedicalRecord
import json.RequestJsonFormats.immSetFormat
import json.medicalrecords.MedicalRecordJsonFormat.medicalRecordJsonFormat
import spray.json.JsonParser

import scala.concurrent.ExecutionContextExecutor


class SurgeonActor(doctorID: DoctorID) extends Actor with ActorLogging {

  val http: HttpExt = Http(Client.system)
  implicit val system: ClassicActorSystemProvider = Client.system
  implicit val executionContext: ExecutionContextExecutor = Client.system.classicSystem.dispatcher
  var token: Option[String] = None


  private lazy val onInteractionBehaviour: Receive = {
    case InsertMedicalRecordMessage(medicalRecord) =>
      insertMedicalRecordRequest(token.getOrElse(""), medicalRecord).pipeTo(self)
      this.context become onAttendResponseInsertMedicalRecordMessageBehaviour
    case UpdateMedicalRecordMessage(medicalRecord) =>
      updateMedicalRecordRequest(token.getOrElse(""), medicalRecord).pipeTo(self)
      this.context become onAttendResponseUpdateMedicalRecordMessageBehaviour
    case AllMedicalRecordsMessage =>
      allMedicalRecordsRequest(token.getOrElse(""), doctorID).pipeTo(self)
      this.context become onAttendResponseAllMedicalRecordsMessageBehaviour
  }

  //  private lazy val onAttendResponseSurgeonLoginMessageBehaviour: Receive = {
  //    case HttpResponse(StatusCodes.OK, _, entity, _) =>
  //      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
  //        token = Some(
  //          JsonParser(body.utf8String).asJsObject.getFields("token").mkString replaceAll("[\"]", "")
  //        )
  //      }
  //      this.context become onInteractionBehaviour
  //    case resp@HttpResponse(code, _, _, _) =>
  //      println("Error: " + code.value)
  //      resp.discardEntityBytes()
  //      this.context become onInteractionBehaviour
  //  }

  private lazy val onAttendResponseInsertMedicalRecordMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(body.utf8String)
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(code.value + ": " + body.utf8String + ".")
      }
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }

  private lazy val onAttendResponseUpdateMedicalRecordMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(body.utf8String)
      }
      this.context become onInteractionBehaviour
    case resp@HttpResponse(code, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        println(code.value + ": " + body.utf8String + ".")
      }
      resp.discardEntityBytes()
      this.context become onInteractionBehaviour
  }

  private lazy val onAttendResponseAllMedicalRecordsMessageBehaviour: Receive = {
    case HttpResponse(StatusCodes.OK, _, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        val medicalRecords: Set[MedicalRecord] = JsonParser(body.utf8String).convertTo[Set[MedicalRecord]]
        if (medicalRecords.nonEmpty)
          println(medicalRecords)
        else
          println("No medical records available.")
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