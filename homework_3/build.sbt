name := "homework_3"

version := "0.1"

scalaVersion := "2.13.6"

assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs@_*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "ch.qos.logback" % "logback-classic" % "1.2.6",
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.1",
  "com.amazonaws" % "aws-lambda-java-events" % "3.10.0",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.12.90",
  "com.thesamet.scalapb" %% "scalapb-json4s" % "0.12.0",
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "org.gnieh" % "logback-config" % "0.4.0",
  "org.scala-stm" %% "scala-stm" % "0.11.1",
  "com.twitter" %% "finagle-http" % "21.10.0"
)

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)


assemblyJarName in assembly := "homework3.jar"


