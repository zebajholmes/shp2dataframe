package github.zebajholmes.shp2df

import org.geotools.data.{DataStore, DataStoreFinder, FeatureSource}
import org.geotools.feature.FeatureCollection
import org.opengis.feature.simple.{SimpleFeature, SimpleFeatureType}

object ShapefileLoader {

  def load (shpFilePath: String): FeatureCollection[SimpleFeatureType, SimpleFeature] = {

    /**
     * take a shapefile, load it and return a geotools FeatureCollection
     */

    //make a java URL object from the shapefile path, this needs to be done bc shapefiles are more than one file
    //make a java Map object of the URL
    val url = new java.net.URL("file://" + shpFilePath)
    val map = new java.util.HashMap[String, java.net.URL]
    map.put("url", url)

    //create a geotools datastore from the shapefile
    val ds: DataStore = DataStoreFinder.getDataStore(map)

    //create a featurecollection from datastore
    val fs: FeatureSource[SimpleFeatureType, SimpleFeature] = ds
      .getFeatureSource( ds.getTypeNames()(0) )

    fs.getFeatures()

  }

}
