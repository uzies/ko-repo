package org.ko.spark.streaming.simple

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming 处理socket数据
  *
  * 测试： nc -lk 6789
  */
object NetworkWordCount {

  def main(args: Array[String]): Unit = {
    //1. 创建spark conf配置
    val sparkConf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("NetworkWordCount")

    //2. 创建StreamingContext需要两个参数: SparkConf 和 batch interval
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    val lines = ssc.socketTextStream("192.168.37.131", 6789)

    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_)

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
