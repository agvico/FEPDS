package utils

import org.apache.commons.math3.distribution.{NormalDistribution, TDistribution}
import org.apache.spark.SparkConf
import problem.qualitymeasures.QualityMeasure


/**
  * miscellaneous useful functions
  */
object Utils {

  /**
    * Calculate the mean of a set of numbers
    *
    * @param list
    * @tparam T
    * @return
    */
  def mean[T: Numeric](list: Seq[T]): Double = {
    val suma: T = list.sum
    val sum: Double = implicitly[Numeric[T]].toDouble(suma)

    sum / list.size.toDouble
  }


  /**
    * Calcualtes the standard deviation of a set of parameters
    *
    * @param list
    * @tparam T
    * @return
    */
  def standardDeviation[T: Numeric](list: Seq[T]): Double = {
    val media = mean(list)
    math.sqrt(list.map(x => {
      val value = implicitly[Numeric[T]].toDouble(x)
      math.pow(value - media, 2)
    })
      .sum / (list.size - 1.0))
  }

  /**
    * It calculates the confidence interval for the mean with the given confidence level
    *
    * @param list
    * @param confidenceLevel
    * @tparam T
    * @return a pair with the bounds of the interval
    */
  def meanConfidenceInterval[T: Numeric](list: Seq[T], confidenceLevel: Double): (Double, Double) = {
    if (list.length == 1) {
      val avg = mean(list)
      return (avg - 0.1, avg + 0.1)
    }

    if (list.length >= 30) {
      zScoreConfidenceInterval(list, confidenceLevel)
    } else {
      tTestConfidenceInterval(list, confidenceLevel)
    }
  }


  private def tTestConfidenceInterval[T: Numeric](list: Seq[T], confidence: Double): (Double, Double) = {
    val t = new TDistribution(list.length - 1)
    val avg: Double = mean(list)
    val n: Double = list.length
    val value: Double = standardDeviation(list) / math.sqrt(n)
    val statistic: Double = t.inverseCumulativeProbability(1 - (1 - confidence) / 2)
    val intervalWidth: Double = statistic * value

    (avg - intervalWidth, avg + intervalWidth)
  }


  private def zScoreConfidenceInterval[T: Numeric](list: Seq[T], confidence: Double): (Double, Double) = {
    val avg = mean(list)
    val std = standardDeviation(list)
    val n = if(std != 0) new NormalDistribution(avg, std)  else new NormalDistribution(avg, 0.000001)
    val z_alfa_2 = n.inverseCumulativeProbability(1 - (1 - confidence / 2))
    val intervalWidth = z_alfa_2 * (std / math.sqrt(list.length))

    (avg - intervalWidth, avg + intervalWidth)
  }



  /**
   * It returns a quality measure class by means of a string name
   * @param name
   * @return
   */
  def getQualityMeasure(name: String): QualityMeasure = {
    Class.forName(
      classOf[QualityMeasure].getPackage.getName +
        "." +
        name)
      .newInstance()
      .asInstanceOf[QualityMeasure]
  }



  /**
   * It returns the spark configuration
   * @return
   */
  def getSparkConfiguration: SparkConf = {
    val conf = new SparkConf()
    conf.registerKryoClasses(
      Array(
        classOf[scala.collection.mutable.WrappedArray.ofRef[_]],
        classOf[org.apache.spark.sql.types.StructType],
        classOf[Array[org.apache.spark.sql.types.StructType]],
        classOf[org.apache.spark.sql.types.StructField],
        classOf[Array[org.apache.spark.sql.types.StructField]],
        Class.forName("org.apache.spark.sql.types.StringType$"),
        Class.forName("org.apache.spark.sql.types.LongType$"),
        Class.forName("org.apache.spark.sql.types.BooleanType$"),
        Class.forName("org.apache.spark.sql.types.DoubleType$"),
        classOf[org.apache.spark.sql.types.Metadata],
        classOf[org.apache.spark.sql.types.ArrayType],
        Class.forName("org.apache.spark.sql.execution.joins.UnsafeHashedRelation"),
        classOf[org.apache.spark.sql.catalyst.InternalRow],
        classOf[Array[org.apache.spark.sql.catalyst.InternalRow]],
        classOf[org.apache.spark.sql.catalyst.expressions.UnsafeRow],
        Class.forName("org.apache.spark.sql.execution.joins.LongHashedRelation"),
        Class.forName("org.apache.spark.sql.execution.joins.LongToUnsafeRowMap"),
        classOf[utils.BitSet],
        classOf[org.apache.spark.sql.types.DataType],
        classOf[Array[org.apache.spark.sql.types.DataType]],
        Class.forName("org.apache.spark.sql.types.NullType$"),
        Class.forName("org.apache.spark.sql.types.IntegerType$"),
        Class.forName("org.apache.spark.sql.types.TimestampType$"),
        Class.forName("org.apache.spark.internal.io.FileCommitProtocol$TaskCommitMessage"),
        Class.forName("scala.collection.immutable.Set$EmptySet$"),
        Class.forName("java.lang.Class")
      )
    )
  }



}
