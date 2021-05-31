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

package server.routes

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{
  complete,
  get,
  onSuccess,
  pathEnd,
  pathPrefix,
  _
}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import cqrs.writemodel.Role.Role
import domainmodel.ChestPainType.ChestPainType
import domainmodel.Gender.Gender
import domainmodel.KinshipDegree.KinshipDegree
import domainmodel.RestingElectrocardiographic.RestingElectrocardiographic
import domainmodel.SlopeST.SlopeST
import domainmodel.Thal.Thal
import domainmodel.generalinfo.AllergyClass.AllergyClass
import domainmodel.generalinfo.BloodType.BloodType
import domainmodel.generalinfo.Rh.Rh
import domainmodel.professionalfigure.Specialization.Specialization
import json.AnamnesisJsonFormat.kinshipDegreeJsonFormat
import json.CardiologyVisitJsonFormat.{
  chestPainTypeJsonFormat,
  restingElectrocardiographicJsonFormat,
  slopeSTJsonFormat,
  thalJsonFormat
}
import json.PatientJsonFormat.genderJsonFormat
import json.RequestJsonFormats.{immSetFormat, roleJsonFormat}
import json.generalinfo.AllergyJsonFormat.allergyClassJsonFormat
import json.generalinfo.BloodGroupJsonFormat.{bloodTypeJsonFormat, rhJsonFormat}
import json.professionalfigure.ProfessionalFigureJsonFormat.specializationJsonFormat
import server.models.Protocol
import server.models.Protocol._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
 * This class contains the implementation of all the routes in order to get enumerations in the db.
 *
 * @param enumerationController the enumeration controller
 * @param system                represent the actor system
 */
class EnumerationRoutes(enumerationController: ActorRef[Protocol.CQRSAction])(
  implicit val system: ActorSystem[_]
) {
  private implicit val timeout: Timeout = Timeout(500.milliseconds)

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  /**
   * Method to get all the roles.
   *
   * @return a set of roles.
   */
  def getRoles: Future[Set[Role]] = enumerationController.ask(GetRoles)

  /**
   * Method to get all the genders.
   *
   * @return a set of genders.
   */
  def getGenders: Future[Set[Gender]] = enumerationController.ask(GetGenders)

  /**
   * Method to get alle the allergies class.
   *
   * @return a set of allergy class.
   */
  def getAllergies: Future[Set[AllergyClass]] =
    enumerationController.ask(GetAllergies)

  /**
   * Method to get all the blood types.
   *
   * @return a set of blood type.
   */
  def getBloodTypes: Future[Set[BloodType]] =
    enumerationController.ask(GetBloodTypes)

  /**
   * Method to get all the rh.
   *
   * @return a set of rh.
   */
  def getRh: Future[Set[Rh]] = enumerationController.ask(GetRh)

  /**
   * Method to get all the specializations.
   *
   * @return a set of specialization.
   */
  def getSpecializations: Future[Set[Specialization]] =
    enumerationController.ask(GetSpecializations)

  /**
   * Method to get all the kinship degrees.
   *
   * @return a set of kinship degree.
   */
  def getKinshipDegrees: Future[Set[KinshipDegree]] =
    enumerationController.ask(GetKinshipDegrees)

  /**
   * Method to get all the chest pain types.
   *
   * @return a set of chest pain type.
   */
  def getChestPainType: Future[Set[ChestPainType]] =
    enumerationController.ask(GetChestPainTypes)

  /**
   * Method to get all the resting electrocardiographic.
   *
   * @return a set of resting electrocardiographic.
   */
  def getRestingECG: Future[Set[RestingElectrocardiographic]] =
    enumerationController.ask(GetRestingECG)

  /**
   * Method to get all the slope ST.
   *
   * @return a set of slope ST.
   */
  def getSlopeST: Future[Set[SlopeST]] = enumerationController.ask(GetSlopeST)

  /**
   * Method to get all the thals.
   *
   * @return a set of thal.
   */
  def getThal: Future[Set[Thal]] = enumerationController.ask(GetThals)

  val enumerationRoutes: Route =
    pathPrefix("api") {
      pathPrefix("roles") {
        pathEnd {
          get {

            onSuccess(getRoles) { response =>
              complete(StatusCodes.Accepted, response)
            }
          }
        }
      } ~
        pathPrefix("genders") {
          pathEnd {
            get {

              onSuccess(getGenders) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~ pathPrefix("allergies") {
          pathEnd {
            get {

              onSuccess(getAllergies) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("bloodtypes") {
          pathEnd {
            get {

              onSuccess(getBloodTypes) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("rh") {
          pathEnd {
            get {

              onSuccess(getRh) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("specializations") {
          pathEnd {
            get {

              onSuccess(getSpecializations) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("kinshipdegrees") {
          pathEnd {
            get {

              onSuccess(getKinshipDegrees) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("chestpaintypes") {
          pathEnd {
            get {

              onSuccess(getChestPainType) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("restingecg") {
          pathEnd {
            get {

              onSuccess(getRestingECG) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("slopest") {
          pathEnd {
            get {

              onSuccess(getSlopeST) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        } ~
        pathPrefix("thals") {
          pathEnd {
            get {

              onSuccess(getThal) { response =>
                complete(StatusCodes.Accepted, response)
              }
            }
          }
        }
    }
}
