// build.sbt

scalaVersion := "2.11.4"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.0",
  "com.twitter"       %% "util-eval" % "6.24.0"
)
