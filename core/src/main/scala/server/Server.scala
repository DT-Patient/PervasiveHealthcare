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

package server

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import behaviours.CardiologyDiseasesPredictor
import cqrs.readmodel.ReadModel
import digitaltwins.PatientDigitalTwin
import server.controllers._
import server.routes._

import scala.util.{Failure, Success}

object Server {

  //#start-http-server
  private def startHttpServer(
    routes: Route
  )(implicit system: ActorSystem[_]): Unit = {
    // Akka HTTP still needs a classic ActorSystem to start
    import system.executionContext

    val futureBinding = Http().newServerAt("localhost", 8080).bind(routes)
    futureBinding.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        println(
          s"Server online at http://${address.getHostString}:${address.getPort}/"
        )
      case Failure(ex) =>
        println("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }

  /**
   * Start method for server.
   */
  def start(): Unit = {
    val rootBehavior = Behaviors.setup[Nothing] { context =>
      val administratorControllerActor =
        context.spawn(AdministratorController(), "AdministratorControllerActor")
      context.watch(administratorControllerActor)

      val surgeonControllerActor =
        context.spawn(SurgeonController(), "SurgeonControllerActor")
      context.watch(surgeonControllerActor)

      val wardnurseControllerActor =
        context.spawn(WardNurseController(), "WardNurseControllerActor")
      context.watch(wardnurseControllerActor)

      val rescuerControllerActor =
        context.spawn(RescuerController(), "RescuerControllerActor")
      context.watch(rescuerControllerActor)

      val generalPractitionerControllerActor = context.spawn(
        GeneralPractitionerController(),
        "GeneralPractitionerControllerActor"
      )
      context.watch(generalPractitionerControllerActor)

      val anesthetistControllerActor =
        context.spawn(AnesthetistController(), "AnesthetistControllerActor")
      context.watch(anesthetistControllerActor)

      val instrumentalistControllerActor = context.spawn(
        InstrumentalistController(),
        "InstrumentalistControllerActor"
      )
      context.watch(instrumentalistControllerActor)

      val cardiologistControllerActor =
        context.spawn(CardiologistController(), "CardiologistControllerActor")
      context.watch(cardiologistControllerActor)

      val authenticationControllerActor = context.spawn(
        AuthenticationController(),
        "AuthenticationControllerActor"
      )
      context.watch(authenticationControllerActor)

      val enumerationControllerActor =
        context.spawn(EnumerationController(), "EnumerationControllerActor")
      context.watch(enumerationControllerActor)

      val commonControllerActor =
        context.spawn(CommonController(), "CommonControllerActor")
      context.watch(commonControllerActor)

      val administratorRoutes =
        new AdministratorRoutes(administratorControllerActor)(context.system)
      val surgeonRoutes =
        new SurgeonRoutes(surgeonControllerActor)(context.system)
      val wardNurseRoutes =
        new WardNurseRoutes(wardnurseControllerActor)(context.system)
      val rescuerRoutes =
        new RescuerRoutes(rescuerControllerActor)(context.system)
      val generalPractitionerRoutes = new GeneralPractitionerRoutes(
        generalPractitionerControllerActor
      )(context.system)
      val anesthetistRoutes =
        new AnesthetistRoutes(anesthetistControllerActor)(context.system)
      val instrumentalistRoutes = new InstrumentalistRoutes(
        instrumentalistControllerActor
      )(context.system)
      val cardiologistRoutes =
        new CardiologistRoutes(cardiologistControllerActor)(context.system)
      val authenticationRoutes =
        new AuthenticationRoutes(authenticationControllerActor)(context.system)
      val enumerationRoutes =
        new EnumerationRoutes(enumerationControllerActor)(context.system)
      val commonRoutes = new CommonRoutes(commonControllerActor)(context.system)

      val mainRoutes = new Routes(
        administratorRoutes,
        surgeonRoutes,
        wardNurseRoutes,
        rescuerRoutes,
        generalPractitionerRoutes,
        anesthetistRoutes,
        instrumentalistRoutes,
        cardiologistRoutes,
        authenticationRoutes,
        enumerationRoutes,
        commonRoutes
      )

      startHttpServer(mainRoutes.routes)(context.system)

      Behaviors.empty
    }

    ReadModel.initialize()
    CardiologyDiseasesPredictor.train()
    PatientDigitalTwin.initialize()
    val system = ActorSystem[Nothing](rootBehavior, "PervasiveHealthcare")
  }

  /**
   * Server main to launch it.
   *
   * @param args , args.
   */
  def main(args: Array[String]): Unit = {
    start()
  }
}
