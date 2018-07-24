package github.zebajholmes.shp2df

import github.zebajholmes.shp2df.ShapefileLoader
import org.geotools.feature.FeatureCollection
import org.opengis.feature.simple.{SimpleFeature, SimpleFeatureType}
import org.scalatest.FunSpec

class ShapefileLoaderTest extends FunSpec{

  describe("ShapefileLoader tests:") {

    val tester = ShapefileLoader.load(getClass.getResource("/tiger_shapefile").getPath)

    it("affirm Java FeatureCollection created and that it is not empty") {

      assert(tester.isInstanceOf[FeatureCollection[SimpleFeatureType, SimpleFeature]])
      assert(!tester.isEmpty)

    }

    it("affirm correct # of features in collection in test data") {

      assert( tester.size() === 831 )

    }

  }

}
