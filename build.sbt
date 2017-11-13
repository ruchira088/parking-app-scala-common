import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.myob",
      scalaVersion := "2.12.4",
      version      := "0.0.2"
    )),
    name := "scala-common",
    libraryDependencies += play,
    libraryDependencies += scalaTest % Test
  )

publishTo := Some("Artifactory Realm" at "http://artifactory.myobparking.io/artifactory/sbt-dev")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")