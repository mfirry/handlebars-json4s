name := "handlebars-json4s"

organization := "mfirry"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.9.2", "2.10.4")

scalacOptions ++= Seq("-feature", "-language:postfixOps")

libraryDependencies ++= Seq(
	"com.github.jknack" % "handlebars" % "2.0.0",
	"org.json4s" %% "json4s-jackson" % "3.2.11",
	"org.specs2" %% "specs2" % "2.4.14" % "test"
)

pomIncludeRepository := { x => false }

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"