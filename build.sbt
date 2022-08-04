scalaVersion := "2.13.8"

name := "kuzminki-zio"

version := "0.9.3"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

lazy val root = (project in file("."))
  .settings(
    name := "kuzminki-zio",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % "2.13.8",
      "org.postgresql" % "postgresql" % "42.2.24",
      "dev.zio" %% "zio" % "1.0.12"
    )
  )

