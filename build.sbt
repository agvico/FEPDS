name := "FEPDS"

version := "1."

scalaVersion := "2.11.6"

val jMetalVersion = "5.9"
val sparkVersion = "2.4.4"

mainClass in Compile := Some("Main")

resolvers += "OW2 public" at "https://repository.ow2.org/nexus/content/repositories/public/"
resolvers += "Maven Central repository" at "https://repo1.maven.org/maven2"


libraryDependencies ++= Seq(

  // JMetal
  "org.uma.jmetal" % "jmetal-core" % jMetalVersion exclude("nz.ac.waikato.cms.weka", "weka-stable"),
  "org.uma.jmetal" % "jmetal-algorithm" % jMetalVersion exclude("nz.ac.waikato.cms.weka", "weka-stable"),

  // PicoCLI
  "info.picocli" % "picocli" % "3.8.0",

  // MOA
  "nz.ac.waikato.cms.moa" % "moa" % "2019.05.0",

  // Spark
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion % "provided",

  "org.apache.commons" % "commons-math3" % "3.2"

)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

/*assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("org.apache.kafka.**" -> "shadeio.@1").inAll
)*/
