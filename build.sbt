// Project name (artifact name in Maven)
name := """java-dependency"""

// orgnization name (e.g., the package name of the project)
organization := "software.reinvent"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.1"

// project description
description := "Template for a sbt/maven dependency."

// Enables publishing to maven repo
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://maven.reinvent-software.de/nexus/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}

overridePublishBothSettings

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false


resolvers ++= Seq(
  Resolver.mavenLocal,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/repositories/public"
)


libraryDependencies ++= Seq(

  // Commons
  "org.apache.commons" % "commons-lang3" % "3.5",
  "com.google.guava" % "guava" % "20.0",
  "org.apache.commons" % "commons-collections4" % "4.1",
  "commons-io" % "commons-io" % "2.5",
  "com.typesafe" % "config" % "1.3.1",
  "org.unbescape" % "unbescape" % "1.1.4.RELEASE",
  "com.github.rholder" % "guava-retrying" % "2.0.0" exclude("com.google.guava", "guava"),
  
  // READABILITY
  "com.github.mfornos" % "humanize-slim" % "1.2.2" exclude("com.google.guava", "guava"),


  // LOGGING
  "ch.qos.logback" % "logback-classic" % "1.1.8",
  "ch.qos.logback" % "logback-core" % "1.1.8",


  // TEST
  "org.assertj" % "assertj-core" % "3.6.1" % "test",
  "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava"),
  "com.novocode" % "junit-interface" % "0.11" % "test->default",
  "org.jukito" % "jukito" % "1.4.1" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
