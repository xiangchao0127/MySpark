import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util.concurrent.Executors
import java.util.{Date, Random}

import model.{DirectionEnum, Weather}
import org.apache.spark.{SparkConf, SparkContext}
import service.WeatherImpl
import util.DateUtil

import scala.tools.scalap.scalax.util.StringUtil

/**
  * Created by WJ on 2017/1/4.
  */
object IPLocation {
  private val airQuality = Array("优", "良", "劣")
  private val cloudState = Array("多云", "下雨", "多云转小雨", "多云转阵雨", "小雨转晴", "小雨转多云", "阴天", "雷阵雨", "多云转阴")

  def data2MySQL() ={
    var conn:Connection = null
    var ps:PreparedStatement = null
    val sql = "INSERT INTO weather_ther VALUES(?,?,?,?,?,?)"
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather?useUnicode=true&characterEncoding=UTF-8", "root", "")

        val weather = genData();
        ps = conn.prepareStatement(sql)
        ps.setString(1,weather.getNo)
        ps.setString(2,DateUtil.date2Str(weather.getDate))
        ps.setString(3,weather.getDirection.getName)
        ps.setString(4,weather.getAirQuality)
        ps.setString(5,weather.getCloudState)
        ps.setInt(6,weather.getTemp)
        ps.executeUpdate()

    }catch{
      case e:Exception => println("Mysql Exception")
    }finally{
      if(ps != null)
        ps.close()
      if(conn != null)
        conn.close()
    }
  }

//  def ip2Long(ip: String): Long = {
//    val fragments = ip.split("[.]")
//    var ipNum = 0L
//    for (i <- 0 until fragments.length){
//      ipNum =  fragments(i).toLong | ipNum << 8L
//    }
//    ipNum
//  }
//
//  def binarySearch(lines:Array[(String,String,String)],ip:Long) :Int ={
//    var low = 0
//    var high = lines.length -1
//    while(low <= high){
//      val middle = (low + high) / 2
//      if((ip >= lines(middle)._1.toLong) && (ip <= lines(middle)._2.toLong))
//        return middle
//      if(ip < lines(middle)._1.toLong)
//        high = middle -1
//      else{
//        low = middle + 1
//      }
//    }
//    -1
//  }
//
  def genData():Weather={
    val weather = new Weather();
    weather.setNo(new Date().getTime + new Random().nextInt(1000000) + "") //编号

    weather.setDate(new Date) //时间

    weather.setDirection(DirectionEnum.getEnumByCode(String.valueOf(new Random().nextInt(4) + 1))) //风向

    weather.setAirQuality(airQuality(new Random().nextInt(3))) //空气质量

    weather.setCloudState(cloudState(new Random().nextInt(9))) //多云或下雨

    weather.setTemp(new Random().nextInt(50)) //温度
    weather
  }

  def main(args: Array[String]): Unit = {
    val thread = Executors.newFixedThreadPool(3);
    try{
    for(i <- 1 to 3){
      thread.execute(new IPLocation())
    }
    }finally {
      thread.shutdown()
    }

  }
//
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("IPLocation").setMaster("local[2]")
//    val sc = new SparkContext(conf)
//    val ipRulesRdd = sc.textFile("e://Test/ip.txt").map(lines =>{
//      val fields = lines.split("\\|")
//      val start_num = fields(2)
//      val end_num = fields(3)
//      val province = fields(6)
//      (start_num,end_num,province)
//    })
//    //全部的IP映射规则
//    val ipRulesArrary = ipRulesRdd.collect()
//
//    //广播规则,这个是由Driver向worker中广播规则
//    val ipRulesBroadcast = sc.broadcast(ipRulesArrary)
//
//    //加载要处理的数据
//    val ipsRdd = sc.textFile("e://Test/access_log").map(line =>{
//      val fields = line.split("\\|")
//      fields(1)
//    })
//
//    val result = ipsRdd.map(ip =>{
//      val ipNum = ip2Long(ip)
//      val index = binarySearch(ipRulesBroadcast.value,ipNum)
//      val info = ipRulesBroadcast.value(index)
//      info
//    }).map(t => {(t._3,1)}).reduceByKey(_+_)
//
//    //将数据写入数据库中
//    result.foreachPartition(data2MySQL)
//
//    println(result.collect().toBuffer)
//    sc.stop()
//
//  }


}

class IPLocation extends Runnable{
  override def run(): Unit = {
    for(i <- 1 to 1000){
      IPLocation.data2MySQL()
    }
  }
}
