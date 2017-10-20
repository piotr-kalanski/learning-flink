name := "learning-flink"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.flink" %% "flink-scala" %  "1.3.2",
  "org.apache.flink" %% "flink-streaming-scala" %  "1.3.2",
  "org.apache.flink" %% "flink-connector-kafka-0.10" % "1.3.2",
  "org.apache.flink" %% "flink-connector-elasticsearch5" % "1.3.2",
  "com.sksamuel.avro4s" %% "avro4s-core" % "1.6.4"
)