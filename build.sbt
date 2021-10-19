name := "zio-sandbox"

version := "0.1"

val scalaCommonVersion = "2.13.6"

lazy val `zio1` = (project in file("./zio1"))
  .settings(
    scalaVersion := scalaCommonVersion,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zio1Version
    )
  )

lazy val `zio2` = (project in file("./zio2"))
  .settings(
    scalaVersion := scalaCommonVersion,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zio2Version
    )
  )

lazy val zio1Version = "1.0.12"
lazy val zio2Version = "2.0.0-M4"

run / fork := true
