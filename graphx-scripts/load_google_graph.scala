import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object GoogleWebGraphApp {
  def main(args: Array[String]): Unit = {
    // Create a SparkSession
    val spark = SparkSession.builder
      .appName("GraphX Google Web Graph")
      .config("spark.master", "spark://spark-master:7077") // Change to your Spark master URL
      .getOrCreate()

    // Get the SparkContext from SparkSession
    val sc = spark.sparkContext

    // Load the edges as a graph from the edge list file
    val graph = GraphLoader.edgeListFile(sc, "/opt/graphx-scripts/web-Google.txt")

    // Run PageRank algorithm
    val ranks = graph.pageRank(0.01).vertices

    // Print the result
    println("PageRank results:")
    ranks.collect.foreach(println)

    // Stop the Spark session
    spark.stop()
  }
}
