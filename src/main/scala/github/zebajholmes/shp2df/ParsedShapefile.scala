package github.zebajholmes.shp2df

import org.apache.spark.sql.{Column, DataFrame}
import org.apache.spark.sql.types.{DataType, LongType, StringType}
import org.geotools.feature.FeatureCollection
import org.opengis.feature.simple.{SimpleFeature, SimpleFeatureType}
import org.apache.spark.sql.functions.col

class ParsedShapefile (inputSHP: String) {

  val fc: FeatureCollection[SimpleFeatureType, SimpleFeature] = ShapefileLoader.load(inputSHP)

  val shpName: String = fc.getSchema.getTypeName

  val numFields: Int = fc.getSchema.getAttributeDescriptors.size()

  val fieldList: List[String] = (
    for ( i <- 1 until numFields ) //don't count the geom until end
      yield fc.getSchema.getAttributeDescriptors.get(i).getLocalName.toLowerCase()
    ).toList :+ "geom"

  val typeList: List[String] = (
    for ( i <- 1 until numFields ) //don't count the geom until end
      yield "(?<=:)(.*)(?=>)".r.findFirstIn(fc.getSchema.getAttributeDescriptors.get(i).toString).get
    ).toList :+ "String"

  val geomType: String = "(?<=<)(.*)(?=>)".r.findFirstIn(fc.getSchema.getAttributeDescriptors.get(0).toString)
    .get.split(":")(0)

  val schema: List[(String, String)] = fieldList zip typeList

  val df: DataFrame = DataFrameMaker.makeDF(this)

}
