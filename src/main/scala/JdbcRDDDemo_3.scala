import java.sql.DriverManager
import java.util

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by WJ on 2017/1/5.
  */
object JdbcRDDDemo_3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JdbcRDDDemo_3").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val connection =() =>{
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection("jdbc:mysql://localhost:3306/weather","root","")
    }
    val start = System.currentTimeMillis();
    val jdbcRDD = new JdbcRDD(
      sc,
      connection,
      "SELECT * from weather_ther where temp >= ? AND temp < ?",
      30,40,1,
      r =>{
        val no = r.getString(1)
        val cloud_time = r.getDate(2)
        val direction = r.getString(3)
        val airQuality = r.getString(4)
        val cloudState = r.getString(5)
        val temp = r.getInt(6)
        (no,cloud_time,direction,airQuality,cloudState,temp)
      }
    )
    val jdbcRDDC = jdbcRDD.collect()
    var i = 0
    jdbcRDDC.map(line =>{
      println("no:"+line._1)
      println("cloud_time:"+line._2)
      println("direction:"+line._3)
      println("airQuality:"+line._4)
      println("cloudState:"+line._5)
      println("temp:"+line._6)
      println("------------------------------")
      i=i+1
    })
    println("总共 "+i+ "条")
    sc.stop
    println("耗时"+(System.currentTimeMillis()-start) +"毫秒")
  }
}
