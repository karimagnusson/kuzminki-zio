scalaVersion := "2.12.12"

name := "kuzminki-zio"

version := "0.9.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.12.12",
  "dev.zio" %% "zio" % "1.0.12",
  //"dev.zio" %% "zio-streams" % "1.0.12",
  "org.postgresql" % "postgresql" % "42.2.24"
)

