package github.zebajholmes.shp2df

import github.zebajholmes.shp2df.{DataFrameMaker, ParsedShapefile, SparkSessionWrapper}
import org.scalatest.FunSpec

class DataFrameMakerTest extends FunSpec with SparkSessionWrapper {

  describe("DataFrameLoader tests:") {

    val tester = new ParsedShapefile(getClass.getResource("/tiger_shapefile").getPath)

    it("assert dataframe is the same length as the raw data from the test file") {

      assert(tester.fc.toArray.length == DataFrameMaker.makeDF(tester).count)

    }

    it("assert that dataframe schema matches the parsed shapefile schema from the test file") {

      assert(DataFrameMaker.makeDF(tester).schema.toString == "StructType(StructField(ansicode,StringType,true), " +
        "StructField(areaid,StringType,true), StructField(fullname,StringType,true), StructField(mtfcc,StringType,true), " +
        "StructField(aland,LongType,true), StructField(awater,LongType,true), StructField(intptlat,StringType,true), " +
        "StructField(intptlon,StringType,true), StructField(geom,StringType,true))")

    }

  }

}
