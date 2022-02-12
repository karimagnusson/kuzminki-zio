scalaVersion := "2.12.12"

name := "kuzminki-zio"

version := "0.9.2"

lazy val root = (project in file("."))
  .settings(
    name := "kuzminki-zio",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % "2.12.12",
      "org.postgresql" % "postgresql" % "42.2.24",
      "dev.zio" %% "zio" % "1.0.12"
    )
  )

