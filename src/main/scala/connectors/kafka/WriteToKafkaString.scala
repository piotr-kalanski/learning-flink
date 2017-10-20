package connectors.kafka

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object WriteToKafkaString extends App {
  // get the execution environment
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

  val stream = env.fromElements("a", "b", "c", "d")
  env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

  val LOCAL_KAFKA_BROKER = "localhost:9092"
  val KAFKA_TOPIC = "test_string"

  stream.addSink(
    new FlinkKafkaProducer010[String](
      LOCAL_KAFKA_BROKER,
      KAFKA_TOPIC,
      new SimpleStringSchema
    )
  )

  env.execute("Write to Kafka Example")
}
