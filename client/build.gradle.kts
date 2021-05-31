plugins{
    id("common-scala")
}

dependencies{
    val scalaVersion = "2.12"
    implementation(project(":core"))

    testRuntimeOnly("org.scala-lang.modules:scala-xml_$scalaVersion:_")
    testImplementation("junit:junit:_")
    testImplementation("org.scalatest:scalatest_$scalaVersion:_")
    testImplementation("org.scalatestplus:junit-4-12_$scalaVersion:_")
    testImplementation("com.typesafe.akka:akka-http-testkit_$scalaVersion:_")
    testImplementation("com.typesafe.akka:akka-actor-testkit-typed_$scalaVersion:_")

    testRuntimeOnly("org.scala-lang.modules:scala-xml_$scalaVersion:_")
}