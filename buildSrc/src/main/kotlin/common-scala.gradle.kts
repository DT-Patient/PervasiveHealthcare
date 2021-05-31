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

plugins{
    application
    scala
    java
    jacoco
    id("org.danilopianini.git-sensitive-semantic-versioning")
    id("com.diffplug.spotless")
}

group = "io.github.DT-Patient"

repositories{
    jcenter()
}

spotless {
    // Scala formatting with Scalafmt.
    scala {
        scalafmt("2.7.5").configFile("${rootDir.absolutePath}/.scalafmt.conf")
    }
}


tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("${rootDir.absolutePath}/build/reports/jacoco/report.xml")
        html.isEnabled = true
        html.destination = file("${rootDir.absolutePath}/build/reports/jacoco/jacocoHtml")
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
dependencies{
    val scalaVersion = "2.12"

    implementation("org.mongodb.scala:mongo-scala-driver_$scalaVersion:_")
    implementation("io.spray:spray-json_$scalaVersion:_")
    implementation("com.lightbend.akka:akka-stream-alpakka-mongodb_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-stream_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-slf4j_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-http_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-http-spray-json_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-persistence-typed_$scalaVersion:_")
    implementation("xyz.driver:spray-json-derivation_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-actor-typed_$scalaVersion:_")
    implementation("org.scala-lang.modules:scala-swing_$scalaVersion:_")
    implementation("org.apache.spark:spark-core_$scalaVersion:_")
    implementation("org.apache.spark:spark-sql_$scalaVersion:_")
    implementation("org.apache.spark:spark-mllib_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-http-bom_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-stream-testkit_$scalaVersion:_")
    implementation("com.typesafe.akka:akka-http-testkit_$scalaVersion:_")


    testImplementation("junit:junit:_")
    testImplementation("org.scalatest:scalatest_$scalaVersion:_")
    testImplementation("org.scalatestplus:junit-4-12_$scalaVersion:_")
    testImplementation("com.typesafe.akka:akka-http-testkit_$scalaVersion:_")
    testImplementation("com.typesafe.akka:akka-actor-testkit-typed_$scalaVersion:_")

    testRuntimeOnly("org.scala-lang.modules:scala-xml_$scalaVersion:_")
}

gitSemVer {
    version = computeGitSemVer()
}