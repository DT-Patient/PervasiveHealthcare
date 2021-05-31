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

package cqrs.readmodel.eventsourcing

import cqrs.readmodel.ReadModel.database
import cqrs.readmodel.eventjsonformat.EventJsonFormat.{
  insertAnesthetistEventJsonFormat,
  insertCardiologistEventJsonFormat,
  insertCardiologyInfoEventJsonFormat,
  insertCardiologyPredictionsEventJsonFormat,
  insertGeneralInfoEventJsonFormat,
  insertGeneralPractitionerEventJsonFormat,
  insertGeneralPractitionerInfoEventJsonFormat,
  insertInstrumentalistEventJsonFormat,
  insertMedicalRecordEventJsonFormat,
  insertPatientInfoEventJsonFormat,
  insertRescuerEventJsonFormat,
  insertSurgeonEventJsonFormat,
  insertWardNurseEventJsonFormat,
  updateAnesthetistEventJsonFormat,
  updateCardiologistEventJsonFormat,
  updateCardiologyInfoEventJsonFormat,
  updateCardiologyPredictionsEventJsonFormat,
  updateGeneralInfoEventJsonFormat,
  updateGeneralPractitionerEventJsonFormat,
  updateGeneralPractitionerInfoEventJsonFormat,
  updateInstrumentalistEventJsonFormat,
  updateMedicalRecordEventJsonFormat,
  updatePatientInfoEventJsonFormat,
  updateRescuerEventJsonFormat,
  updateSurgeonEventJsonFormat,
  updateWardNurseEventJsonFormat
}
import cqrs.readmodel.eventsourcing.EventType.EventType
import domainmodel._
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Filters.{and, equal, or}
import spray.json.{JsValue, JsonParser, enrichAny}

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Store of event for event sourcing.
 */
object EventStore {

  val eventsCollection: MongoCollection[BsonDocument] =
    database.getCollection[BsonDocument]("events")

  private def createEventDocument(event: Event): BsonDocument =
    event match {
      case event: InsertSurgeonEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertSurgeonEvent].toJson.compactPrint
        )
      case event: UpdateSurgeonEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateSurgeonEvent].toJson.compactPrint
        )
      case event: InsertAnesthetistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertAnesthetistEvent].toJson.compactPrint
        )
      case event: UpdateAnesthetistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateAnesthetistEvent].toJson.compactPrint
        )
      case event: InsertGeneralPractitionerEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertGeneralPractitionerEvent].toJson.compactPrint
        )
      case event: UpdateGeneralPractitionerEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateGeneralPractitionerEvent].toJson.compactPrint
        )
      case event: InsertInstrumentalistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertInstrumentalistEvent].toJson.compactPrint
        )
      case event: UpdateInstrumentalistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateInstrumentalistEvent].toJson.compactPrint
        )
      case event: InsertWardNurseEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertWardNurseEvent].toJson.compactPrint
        )
      case event: UpdateWardNurseEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateWardNurseEvent].toJson.compactPrint
        )
      case event: InsertRescuerEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertRescuerEvent].toJson.compactPrint
        )
      case event: UpdateRescuerEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateRescuerEvent].toJson.compactPrint
        )
      case event: InsertCardiologistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertCardiologistEvent].toJson.compactPrint
        )
      case event: UpdateCardiologistEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateCardiologistEvent].toJson.compactPrint
        )
      case event: InsertPatientInfoEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertPatientInfoEvent].toJson.compactPrint
        )
      case event: UpdatePatientInfoEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdatePatientInfoEvent].toJson.compactPrint
        )
      case event: InsertMedicalRecordEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertMedicalRecordEvent].toJson.compactPrint
        )
      case event: UpdateMedicalRecordEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateMedicalRecordEvent].toJson.compactPrint
        )
      case event: InsertGeneralInfoEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertGeneralInfoEvent].toJson.compactPrint
        )
      case event: UpdateGeneralInfoEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateGeneralInfoEvent].toJson.compactPrint
        )
      case event: InsertGeneralPractitionerInfoEvent =>
        BsonDocument.apply(
          event
            .asInstanceOf[InsertGeneralPractitionerInfoEvent]
            .toJson
            .compactPrint
        )
      case event: UpdateGeneralPractitionerInfoEvent =>
        BsonDocument.apply(
          event
            .asInstanceOf[UpdateGeneralPractitionerInfoEvent]
            .toJson
            .compactPrint
        )
      case event: InsertCardiologyVisitEvent =>
        BsonDocument.apply(
          event.asInstanceOf[InsertCardiologyVisitEvent].toJson.compactPrint
        )
      case event: UpdateCardiologyVisitEvent =>
        BsonDocument.apply(
          event.asInstanceOf[UpdateCardiologyVisitEvent].toJson.compactPrint
        )
      case event: InsertCardiologyPredictionsEvent =>
        BsonDocument.apply(
          event
            .asInstanceOf[InsertCardiologyPredictionsEvent]
            .toJson
            .compactPrint
        )
      case event: UpdateCardiologyPredictionsEvent =>
        BsonDocument.apply(
          event
            .asInstanceOf[UpdateCardiologyPredictionsEvent]
            .toJson
            .compactPrint
        )
    }

  /**
   * Add event at event store.
   *
   * @param event , event to add.
   */
  def addEvent(event: Event): Unit = {
    val document: BsonDocument = createEventDocument(event)
    Await.result(
      eventsCollection.insertOne(document).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
  }

  private def convertInEvent(eventType: EventType, json: JsValue): Event =
    eventType match {
      case x if x == EventType.INSERT_SURGEON =>
        json.convertTo[InsertSurgeonEvent]
      case x if x == EventType.UPDATE_SURGEON =>
        json.convertTo[UpdateSurgeonEvent]
      case x if x == EventType.INSERT_ANESTHETIST =>
        json.convertTo[InsertAnesthetistEvent]
      case x if x == EventType.UPDATE_ANESTHETIST =>
        json.convertTo[UpdateAnesthetistEvent]
      case x if x == EventType.INSERT_GENERAL_PRACTITIONER =>
        json.convertTo[InsertGeneralPractitionerEvent]
      case x if x == EventType.UPDATE_GENERAL_PRACTITIONER =>
        json.convertTo[UpdateGeneralPractitionerEvent]
      case x if x == EventType.INSERT_INSTRUMENTALIST =>
        json.convertTo[InsertInstrumentalistEvent]
      case x if x == EventType.UPDATE_INSTRUMENTALIST =>
        json.convertTo[UpdateInstrumentalistEvent]
      case x if x == EventType.INSERT_WARD_NURSE =>
        json.convertTo[InsertWardNurseEvent]
      case x if x == EventType.UPDATE_WARD_NURSE =>
        json.convertTo[UpdateWardNurseEvent]
      case x if x == EventType.INSERT_RESCUER =>
        json.convertTo[InsertRescuerEvent]
      case x if x == EventType.UPDATE_RESCUER =>
        json.convertTo[UpdateRescuerEvent]
      case x if x == EventType.INSERT_CARDIOLOGIST =>
        json.convertTo[InsertCardiologistEvent]
      case x if x == EventType.UPDATE_CARDIOLOGIST =>
        json.convertTo[UpdateCardiologistEvent]
      case x if x == EventType.INSERT_PATIENT_INFO =>
        json.convertTo[InsertPatientInfoEvent]
      case x if x == EventType.UPDATE_PATIENT_INFO =>
        json.convertTo[UpdatePatientInfoEvent]
      case x if x == EventType.INSERT_MEDICAL_RECORD =>
        json.convertTo[InsertMedicalRecordEvent]
      case x if x == EventType.UPDATE_MEDICAL_RECORD =>
        json.convertTo[UpdateMedicalRecordEvent]
      case x if x == EventType.INSERT_GENERAL_INFO =>
        json.convertTo[InsertGeneralInfoEvent]
      case x if x == EventType.UPDATE_GENERAL_INFO =>
        json.convertTo[UpdateGeneralInfoEvent]
      case x if x == EventType.INSERT_GENERAL_PRACTITIONER_INFO =>
        json.convertTo[InsertGeneralPractitionerInfoEvent]
      case x if x == EventType.UPDATE_GENERAL_PRACTITIONER_INFO =>
        json.convertTo[UpdateGeneralPractitionerInfoEvent]
      case x if x == EventType.INSERT_CARDIOLOGY_VISIT =>
        json.convertTo[InsertCardiologyVisitEvent]
      case x if x == EventType.UPDATE_CARDIOLOGY_VISIT =>
        json.convertTo[UpdateCardiologyVisitEvent]
      case x if x == EventType.INSERT_CARDIOLOGY_PREDICTION =>
        json.convertTo[InsertCardiologyPredictionsEvent]
      case x if x == EventType.UPDATE_CARDIOLOGY_PREDICTION =>
        json.convertTo[UpdateCardiologyPredictionsEvent]
    }

  /**
   * Get events from selected id.
   *
   * @param id , id for filter events.
   * @return set of events
   */
  def getEvents(id: ID): Seq[Event] = {
    val res: Seq[BsonDocument] = Await.result(
      eventsCollection.find(equal("id.value", id.value)).toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.nonEmpty) {
      res
        .map(bson =>
          convertInEvent(
            EventType(bson.get("eventID").asInt32().intValue()),
            JsonParser(bson.toString)
          )
        )
        .sortWith((e1, e2) => e1.time.isBefore(e2.time))
    } else Seq.empty
  }

  /**
   * Get all patient from events.
   *
   * @return all patient saved.
   */
  def getAllInsertPatientEvents: Set[PatientID] = {
    val res: Seq[BsonDocument] = Await.result(
      eventsCollection
        .find(equal("eventID", EventType.INSERT_PATIENT_INFO.id))
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.nonEmpty) {
      res
        .map(bson => bson.getDocument("id").get("value").asString().getValue)
        .map(id => PatientID(id))
        .toSet
    } else Set.empty
  }

  /**
   * Get all cardiology visits for a doctor.
   *
   * @param doctorID , doctor ID.
   * @return all cardiology visits for a doctor.
   */
  def getAllCardiologyVisitsForDoctorEvents(doctorID: DoctorID): Set[Event] = {
    val res: Seq[BsonDocument] = Await.result(
      eventsCollection
        .find(
          and(
            Filters.or(
              equal("eventID", EventType.INSERT_CARDIOLOGY_VISIT.id),
              equal("eventID", EventType.UPDATE_CARDIOLOGY_VISIT.id)
            ),
            equal("c.doctorID.value", doctorID.value)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.nonEmpty) {
      res
        .map(bson =>
          convertInEvent(
            EventType(bson.get("eventID").asInt32().intValue()),
            JsonParser(bson.toString)
          )
        )
        .toSet
    } else Set.empty
  }

  /**
   * Get all medical record for a doctor.
   *
   * @param doctorID , doctor ID.
   * @return all medical records for a doctor.
   */
  def getAllMedicalRecordsForDoctorEvents(doctorID: DoctorID): Set[Event] = {
    val res: Seq[BsonDocument] = Await.result(
      eventsCollection
        .find(
          and(
            Filters.or(
              equal("eventID", EventType.INSERT_MEDICAL_RECORD.id),
              equal("eventID", EventType.UPDATE_MEDICAL_RECORD.id)
            ),
            equal("m.doctorID.value", doctorID.value)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.nonEmpty) {
      res
        .map(bson =>
          convertInEvent(
            EventType(bson.get("eventID").asInt32().intValue()),
            JsonParser(bson.toString)
          )
        )
        .toSet
    } else Set.empty
  }

  /**
   * Get all general practitioner info for a doctor.
   *
   * @param doctorID , doctor ID.
   * @return all general practitioner info for a doctor.
   */
  def getAllGeneralPractitionerInfoForDoctorEvents(
    doctorID: DoctorID
  ): Set[Event] = {
    val res: Seq[BsonDocument] = Await.result(
      eventsCollection
        .find(
          and(
            Filters.or(
              equal("eventID", EventType.INSERT_GENERAL_PRACTITIONER_INFO.id),
              equal("eventID", EventType.UPDATE_GENERAL_PRACTITIONER_INFO.id)
            ),
            equal("g.doctorID.value", doctorID.value)
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (res.nonEmpty) {
      res
        .map(bson =>
          convertInEvent(
            EventType(bson.get("eventID").asInt32().intValue()),
            JsonParser(bson.toString)
          )
        )
        .toSet
    } else Set.empty
  }

  /**
   * Get all new predictions for a doctor.
   *
   * @param doctorID , doctor ID.
   * @return all new predictions for a doctor.
   */
  def getNewPredictionsEvents(doctorID: DoctorID): Seq[Event] = {
    val insertedPredBson = Await.result(
      eventsCollection
        .find(
          and(
            equal("c.doctorID.value", doctorID.value),
            or(
              equal("eventID", EventType.INSERT_CARDIOLOGY_PREDICTION.id),
              equal("eventID", EventType.UPDATE_CARDIOLOGY_PREDICTION.id)
            )
          )
        )
        .toFuture(),
      Duration(1, TimeUnit.SECONDS)
    )
    if (insertedPredBson.nonEmpty) {
      insertedPredBson
        .map(bson =>
          convertInEvent(
            EventType(bson.get("eventID").asInt32().intValue()),
            JsonParser(bson.toString)
          )
        )
        .sortWith((e1, e2) => e1.time.isBefore(e2.time))
    } else Seq.empty
  }
}
