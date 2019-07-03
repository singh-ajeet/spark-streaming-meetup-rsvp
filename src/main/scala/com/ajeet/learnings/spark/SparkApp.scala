package com.ajeet.learnings.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf

object SparkApp extends App {

  val spark = SparkSession.builder()
    .appName("Learning Spark")
    .master("local")
    .config("spark.sql.shuffle.partitions", "5")
    .config("spark.sql.crossJoin.enabled", "true")
    .getOrCreate()

  import spark.implicits._

  val person = Seq(
    (0, "Bill Chambers", 0, Seq(100)),
    (1, "Matei Zaharia", 1, Seq(500, 250, 100)),
    (2, "Michael Armbrust", 1, Seq(250, 100)))
    .toDF("id", "name", "graduate_program", "spark_status")

  person.createGlobalTempView("person")

  val graduateProgram = Seq(
    (0, "Masters", "School of Information", "UC Berkeley"),
    (2, "Masters", "EECS", "UC Berkeley"),
    (1, "Ph.D.", "EECS", "UC Berkeley"))
    .toDF("id", "degree", "department", "school")

  graduateProgram.createTempView("graduateProgram")

  val sparkStatus = Seq(
    (500, "Vice President"),
    (250, "PMC Member"),
    (100, "Contributor"))
    .toDF("id", "status")

  val strLength = (x:String) => x.length
  val lengthUDF = udf(strLength)

  spark.udf.register("strLength", strLength)
  sparkStatus.withColumn("length", lengthUDF(sparkStatus.col("id")))
      .show()

  spark.close()
}
