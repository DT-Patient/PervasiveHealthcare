plugins{
    application
    scala
    java
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