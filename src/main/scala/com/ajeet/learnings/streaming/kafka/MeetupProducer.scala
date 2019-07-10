package com.ajeet.learnings.streaming.kafka

import java.net.URL
import java.util.Properties

import com.ajeet.learnings.streaming.kafka.KafkaConfig._
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.errors.RetriableException

object  MeetupProducer  extends App{

  val url = new URL("http://stream.meetup.com/2/rsvps")
  val conn = url.openConnection()
  conn.addRequestProperty("User-Agent",
    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)")
  val jsonFactory = new JsonFactory(new ObjectMapper)
  val parser = jsonFactory.createParser(conn.getInputStream)


  val props = new Properties()
  props.put("bootstrap.servers",BOOTSTRAP_SERVERS)
  props.put("key.serializer", classOf[org.apache.kafka.common.serialization.StringSerializer].getName)
  props.put("value.serializer", classOf[org.apache.kafka.common.serialization.StringSerializer].getName)

  val producer = new KafkaProducer[String,String](props)
  while (parser.nextToken()!=null)  {
    val record = parser.readValueAsTree().toString()

    val producerRecord = new ProducerRecord[String, String]("spatial-topic", record)

    //Asynchronous communication
    val callback: Callback = (metadata: RecordMetadata, exception: Exception) => {
      if(exception != null){
        throw exception
      } else {
        println("Written with offset: " + metadata.offset())
      }
    }
    try {
      producer.send(producerRecord, callback)
    } catch {
      case ex:RetriableException => producer.send(producerRecord, callback)
      case ex: Exception => throw ex
    } finally {
      producer.close()
    }
  }
}
