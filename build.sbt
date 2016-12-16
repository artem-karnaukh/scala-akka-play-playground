name := "My Project"

val commonSettings = Seq(
  organization := "net.example",
  version := "0.1",
  scalaVersion := "2.11.8",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val root = project.in(file("."))
  .aggregate(messages, actors, web)

lazy val messages = project.in(file("modules/messages"))
  .settings(commonSettings:_*)

lazy val actorsDependencies = Seq (
  "com.typesafe.akka" %% "akka-actor" % "2.4.14",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.14"
)

lazy val actors = project.in(file("modules/actors"))
  .dependsOn(messages)
  .settings(commonSettings:_*)
  .settings(libraryDependencies ++= actorsDependencies)

lazy val playDependencies = Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

lazy val web = project.in(file("modules/web"))
   .enablePlugins(PlayScala)
   .dependsOn(messages, actors)
  .settings(commonSettings:_*)
  .settings(libraryDependencies ++= (playDependencies ++ actorsDependencies))

run in Compile <<= (run in Compile in web)