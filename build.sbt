name := "spark_zips"

version := "0.1"

scalaVersion := "2.11.12"

sparkVersion := "2.2.0"

sparkComponents ++= Seq("sql")

resolvers += "osgeo" at "http://download.osgeo.org/webdav/geotools/"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1"
libraryDependencies += "org.datasyslab" % "geospark" % "1.1.3" % "provided"
libraryDependencies += "org.datasyslab" % "geospark-sql_2.2" % "1.1.3" % "provided"
libraryDependencies += "org.datasyslab" % "geospark-viz" % "1.1.3" % "provided"
libraryDependencies += "com.vividsolutions" % "jts" % "1.13"
libraryDependencies += "org.geotools" % "gt-data" % "18.0"



artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + sv.binary + "-" + sparkVersion.value + "_" + module.revision + "." + artifact.extension
}