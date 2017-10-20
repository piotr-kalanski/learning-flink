package connectors.kafka

import java.util.Properties

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object ReadFromKafkaString extends App {
  // get the execution environment
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
  env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

  val LOCAL_KAFKA_BROKER = "localhost:9092"
  val LOCAL_ZOOKEEPER_HOST = "localhost:2181"
  val KAFKA_TOPIC = "test_string"

  // configure Kafka consumer
  val kafkaProps = new Properties
  kafkaProps.setProperty("zookeeper.connect", LOCAL_ZOOKEEPER_HOST)
  kafkaProps.setProperty("bootstrap.servers", LOCAL_KAFKA_BROKER)
  kafkaProps.setProperty("group.id", "testGroup")
  // always read the Kafka topic from the start
  kafkaProps.setProperty("auto.offset.reset", "earliest")

  val stream = env.addSource(
    new FlinkKafkaConsumer010[String](
      KAFKA_TOPIC,
      new SimpleStringSchema,
      kafkaProps
    )
  )

  stream.print()

  env.execute("Read from Kafka Example")
}
