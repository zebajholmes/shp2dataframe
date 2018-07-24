package github.zebajholmes.shp2df

import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {

  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("shape2dataframe")
      .getOrCreate()
  }

}
