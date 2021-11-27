scalaVersion := "2.12.12"

name := "kuzminki-zio"

version := "0.8.0-test"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.12.12",
  "dev.zio" %% "zio" % "1.0.12",
  "org.postgresql" % "postgresql" % "42.2.24"
)
