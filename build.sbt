name := "handlebars-json4s"

organization := "mfirry"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-feature", "-language:postfixOps")

libraryDependencies ++= Seq(
	"com.github.jknack" % "handlebars" % "1.1.2",
	"org.json4s" %% "json4s-jackson" % "3.2.5",
	"org.specs2" %% "specs2" % "2.3.4" % "test"
)

pomIncludeRepository := { x => false }
