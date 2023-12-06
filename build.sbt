import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "citric-liquid",
    idePackagePrefix := Some("cl.uchile.dcc.citric"),
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalafx" %% "scalafx" % "21.0.0-R32"
    )
)

//fork := true