import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RddTest {
  def main(args: Array[String]): Unit = {
   val conf = new SparkConf()
   conf.setAppName("MySparkRddTest").setMaster("local")
   val sc = new SparkContext(conf)
   val rdd = sc.makeRDD(List(1,2,3,4,5,6))
    rdd.foreach(println)

    val rdd01 = sc.makeRDD(List(1,2,3,4,5,6))
    val r01 = rdd01.map { x => x * x }
    println(r01.collect().mkString(","))
    /* Array */
    val rdd02 = sc.makeRDD(Array(1,2,3,4,5,6))
    val r02 = rdd02.filter { x => x < 5}
    println(r02.collect().mkString(","))

    val rdd03 = sc.parallelize(List(1,2,3,4,5,6), 1)
    val r03 = rdd03.map { x => x + 1 }
    println(r03.collect().mkString(","))
    /* Array */
    val rdd04 = sc.parallelize(List(1,2,3,4,5,6), 1)
    val r04 = rdd04.filter { x => x > 3 }
    println(r04.collect().mkString(","))


    val rddInt:RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,2,5,1))
    val rddStr:RDD[String] = sc.parallelize(Array("a","b","c","d","b","a"), 1)
    val rddFile:RDD[String] = sc.textFile("", 1)


//    val rdd02:RDD[Int] = sc.makeRDD(List(2,4,5,1)) val rdd01:RDD[Int] = sc.makeRDD(List(1,3,5,3))

    /* map操作 */
    println("======map操作======")
    println(rddInt.map(x => x + 1).collect().mkString(","))
    println("======map操作======")
    /* filter操作 */
    println("======filter操作======")
    println(rddInt.filter(x => x > 4).collect().mkString(","))
    println("======filter操作======")
    /* flatMap操作 */
    println("======flatMap操作======")
    println(rddFile.flatMap { x => x.split(",") }.first())
    println("======flatMap操作======")
    /* distinct去重操作 */
    println("======distinct去重======")
    println(rddInt.distinct().collect().mkString(","))
    println(rddStr.distinct().collect().mkString(","))
    println("======distinct去重======")
    /* union操作 */
    println("======union操作======")
    println(rdd01.union(rdd02).collect().mkString(","))
    println("======union操作======")
    /* intersection操作 */
    println("======intersection操作======")
    println(rdd01.intersection(rdd02).collect().mkString(","))
    println("======intersection操作======")
    /* subtract操作 */
    println("======subtract操作======")
    println(rdd01.subtract(rdd02).collect().mkString(","))
    println("======subtract操作======")
    /* cartesian操作 */
    println("======cartesian操作======")
    println(rdd01.cartesian(rdd02).collect().mkString(","))
    println("======cartesian操作======")




//    val rddInt:RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,2,5,1))
//    val rddStr:RDD[String] = sc.parallelize(Array("a","b","c","d","b","a"), 1)

    /* count操作 */
    println("======count操作======")
    println(rddInt.count())
    println("======count操作======")
    /* countByValue操作 */
    println("======countByValue操作======")
    println(rddInt.countByValue())
    println("======countByValue操作======")
    /* reduce操作 */
    println("======countByValue操作======")
    println(rddInt.reduce((x ,y) => x + y))
    println("======countByValue操作======")
    /* fold操作 */
    println("======fold操作======")
    println(rddInt.fold(0)((x ,y) => x + y))
    println("======fold操作======")
    /* aggregate操作 */
    println("======aggregate操作======")
    val res:(Int,Int) = rddInt.aggregate((0,0))((x,y) => (x._1 + x._2,y),(x,y) => (x._1 + x._2,y._1 + y._2))
    println(res._1 + "," + res._2)
    println("======aggregate操作======")
    /* foeach操作 */
    println("======foeach操作======")
    println(rddStr.foreach { x => println(x) })
    println("======foeach操作======")

  }
}
