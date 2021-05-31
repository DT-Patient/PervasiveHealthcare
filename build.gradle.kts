plugins {
    jacoco
    scala
}

group = "io.github.enrignagna"

buildscript{ repositories{ mavenCentral()}}
repositories {
    jcenter()
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("${buildDir}/reports/jacoco/report.xml")
        html.isEnabled = true
        html.destination = file("${buildDir}/reports/jacoco/jacocoHtml")
    }
}

dependencies {
    implementation("org.scala-lang:scala-library:_")
}
