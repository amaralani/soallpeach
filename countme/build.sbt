name := "scala"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies+="com.sparkjava" % "spark-core" % "2.9.1"
libraryDependencies+="org.apache.commons" % "commons-lang3" % "3.9"

// No need to run tests while building jar
test in assembly := {}
// Simple and constant jar name
assemblyJarName in assembly := s"app-assembly.jar"
// Merge strategy for assembling conflicts
assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}