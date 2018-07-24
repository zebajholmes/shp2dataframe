package github.zebajholmes.shp2df

import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.sql.SparkSession
import org.datasyslab.geospark.serde.GeoSparkKryoRegistrator
import org.datasyslab.geosparksql.utils.GeoSparkSQLRegistrator

trait SparkSessionWrapper {

  //spark.conf.set("spark.sql.autoBroadcastJoinThreshold", -1)

  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local") //sparkle: master="spark://localhost:7077"
      .appName("CanadianPostal")
      .config("spark.serializer", classOf[KryoSerializer].getName)
      .config("spark.kryo.registrator", classOf[GeoSparkKryoRegistrator].getName)
      .getOrCreate()
  }

  GeoSparkSQLRegistrator.registerAll(spark)

}
