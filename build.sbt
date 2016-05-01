name := "shapeless-experiments"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

exportJars := true

libraryDependencies ++= Seq(
  "com.chuusai" % "shapeless_2.11" % "2.3.0",
  "org.typelevel" % "kittens_2.11" % "1.0.0-M2",
  "org.scalatest"   %% "scalatest"      % "3.0.0-M7" % "test"
)

scalacOptions ++= Seq(
  "-feature",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked"
)