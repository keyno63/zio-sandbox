name := "zio-sandbox"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion
)

lazy val zioVersion = "2.0.0-M4"

run / fork := true
