import sbt._
import Versions._

lazy val jira = SettingKey[String]("jira", "The JIRA issue parameter to be propagated to git commit message.")

lazy val commonSettings = Seq(
  organization := "me.mig.cxb",
  version := (version in ThisBuild).value,
  jira := sys.props.get("JIRA").getOrElse("QA-XXX")
)

name := "cxb"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions in ThisBuild ++= Options.scalacOptions

//=======================================================================================
// Assembly
//=======================================================================================

// Avoid sub-projects generating assembly jars
aggregate in assembly := false

// Exclude tests in assembly
test in assembly := {}

//=======================================================================================
// Publish
//=======================================================================================

publishTo in ThisBuild := {
  val nexus = "https://tools.projectgoth.com/nexus/content/repositories/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "snapshots")
  else
    Some("releases"  at nexus + "releases")
}

libraryDependencies ++= Seq(
  "com.typesafe.akka"         %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka"         %% "akka-contrib"     % akkaVersion,
  "com.typesafe.akka"         %% "akka-agent"       % akkaVersion     % Runtime,
  "com.typesafe.akka"         %% "akka-cluster"     % akkaVersion     % Runtime,
  "com.typesafe.akka"         %% "akka-slf4j"       % akkaVersion     % Runtime,
  "org.slf4j"                 %  "slf4j-api"        % slf4jVersion,
  "org.apache.logging.log4j"  %  "log4j-core"       % log4j2Version   % Runtime,
  "org.apache.logging.log4j"  %  "log4j-api"        % log4j2Version   % Runtime,
  "org.apache.logging.log4j"  %  "log4j-slf4j-impl" % log4j2Version   % Runtime,
  "io.spray"                  %% "spray-routing"    % sprayVersion,
  "io.spray"                  %% "spray-can"        % sprayVersion,
  "io.spray"                  %% "spray-httpx"      % sprayVersion,
  "io.spray"                  %% "spray-json"       % sprayJsonVersion,
  "me.mig.fission"            %% "dao"              % fissionVersion,
  "me.mig.fission"            %% "configuration"    % fissionVersion
)

// scope: test
libraryDependencies ++= Seq(
  "org.testng"              % "testng"                    % testNgVersion   % "test"
)

//=======================================================================================
// Projects
//=======================================================================================

lazy val ace = Project(
  id="cxb",
  base= file(".")
).settings(
    commonSettings: _*
  ).settings(
    releaseSettings: _*
  ).settings(
    addArtifact(Artifact("cxb", "assembly"), sbtassembly.AssemblyKeys.assembly),
    assemblyJarName <<= (name, scalaVersion, version) map ((x,y,z) => "%s_%s-%s-assembly.jar" format(x,y,z))
  )


fork in run := true