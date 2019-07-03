package com.ajeet.learnings.streaming.spark

import com.ajeet.learnings.streaming.meetup.model.RSVPEvent
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MeetupEventStreamingSparkApp extends App {
  @transient lazy val log = Logger.getLogger("MeetupEventStreamingSparkApp")


  val spark = SparkSession.builder()
    .appName("Meetup RSVP Events analytics")
    .master("local")
    .config("spark.sql.shuffle.partitions", "5")
    .config("spark.sql.crossJoin.enabled", "true")
    .config("spark.streaming.blockInterval", "5")
    .getOrCreate()


  val ssc = new StreamingContext(spark.sparkContext, Seconds(2))

  log.info("Spark session started.")
  // implicit val rsvpEncoder = org.apache.spark.sql.Encoders.kryo[RSVPEvent]

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "rsvp",
    "value.deserializer" -> classOf[StringDeserializer],
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("spatial-topic")

  val stream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )
    .map(record => RSVPEvent.parse(record.value))
    //.filter(event => event != null)
    .map(event => {
      if(event == null || event.getVenue == null)
        null
       else
       "POINT (" + (event.getVenue().getLon() + "," +  event.getVenue().getLat() + ")")
    })

  //Printing output of each microbatch on console
  stream.print()

  ssc.start()
  ssc.awaitTermination()
}
