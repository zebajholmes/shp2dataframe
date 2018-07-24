package github.zebajholmes.shp2df

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{split, col}

object DataFrameMaker extends SparkSessionWrapper {

  import spark.implicits._

  def makeDF(parsedShp: ParsedShapefile): DataFrame = {

    val attrSpliterRegex: String = ", Simple(.*?)>="
    val geomSpliterRegex: String = "Simple(.*)>="

    val rawData: Seq[String] = for (f <- parsedShp.fc.toArray) yield f.toString

    rawData.toDF("whole_data")
      .withColumn("temp", split('whole_data,  attrSpliterRegex))
      .select((0 until parsedShp.numFields).map(i => 'temp.getItem(i).as(s"col$i")): _*)
      .withColumn("geom", split('col0, geomSpliterRegex)(1)).drop('col0)
      .toDF(parsedShp.fieldList: _*)
      .select(parsedShp.schema.map(c => col(c._1).cast(c._2)):_*)

  }

}
