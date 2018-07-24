package github.zebajholmes.shp2df

import github.zebajholmes.shp2df.ParsedShapefile
import org.scalatest.FunSpec

class ParsedShapefileTest extends FunSpec {

  describe("ShapefileLoader tests:") {

    val tester = new ParsedShapefile(getClass.getResource("/tiger_shapefile").getPath)
    val tester2 = new ParsedShapefile(getClass.getResource("/vt_shapefile").getPath)

    it("affirm shapefile name parsed correctly for test shapefiles") {

      assert(tester.shpName == "tl_2017_us_mil")
      assert(tester2.shpName == "VT_Digital_Line_Graph_Miscellaneous_Transmission_Lines")

    }

    it("affirm fields and types were parsed correctly for test shapefiles") {

      assert(tester.numFields == 9)
      assert(tester.schema.toString == "List((ansicode,String), (areaid,String), (fullname,String), (mtfcc,String), " +
        "(aland,Long), (awater,Long), (intptlat,String), (intptlon,String), (geom,String))")

      assert(tester2.numFields == 7)
      assert(tester2.schema.toString == "List((objectid,Long), (major1,Long), (minor1,Long), (major2,Long), " +
        "(minor2,Long), (shapestlen,Double), (geom,String))")

    }

    it("affirm geometry feature types where parsed correctly for test shapefiles") {

      assert(tester.geomType == "MultiPolygon")
      assert(tester2.geomType == "MultiLineString")

    }

  }

}
