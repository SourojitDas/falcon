package services

import Configuration
import ch.hsr.geohash.GeoHash
import models.bikestand.BikeStandModel
import models.falcon.Coordinates
import models.falcon.FalconDirectionsModel
import models.falcon.FalconRouteModel
import models.googleMaps.GoogleDirectionsModel
import models.googleMaps.GoogleRouteModel
import models.googleMaps.Leg
import models.googleMaps.parseJson

object GoogleRoute {
    val DrivingMode = "driving"
    val WalkingMode = "walking"
    val BicyclingMode = "bicycling"
    val TransitMode = "transit"


    lateinit var bikeStandOrgin: BikeStandModel
    lateinit var bikeStandDestination: BikeStandModel

    fun getRouteByEndpointsAndMode(
            origin: String,
            destination: String,
            mode: String
    ): models.googleMaps.GoogleDirectionsModel? {
        val payload = mapOf(
                "origin" to origin,
                "destination" to destination,
                "mode" to mode,
                "key" to Configuration.getGoogleMapsServiceApiKey()
        )
        val r = khttp.get(Configuration.getGoogleMapsServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }

    fun getMultiModeRoute(origin: String, destination: String): List<FalconDirectionsModel?> {
        val res = mutableListOf<FalconDirectionsModel>()
        for (mode in listOf(DrivingMode, WalkingMode, BicyclingMode, TransitMode)) {
            val directionsFromGoogle = getRouteByEndpointsAndMode(origin, destination, mode)
            var modifiedDirectionsObject = FalconDirectionsModel()
            modifiedDirectionsObject = _updateDirectionsObject(modifiedDirectionsObject, directionsFromGoogle!!)
            modifiedDirectionsObject.routes = mutableListOf()

            for (route in directionsFromGoogle.routes!!) {
                var newRoute = FalconRouteModel()
                newRoute = _updateRouteObject(newRoute, route)
                newRoute.mode = mode
                modifiedDirectionsObject.routes?.add(newRoute)
            }
            res.add(modifiedDirectionsObject)
        }
        return res
    }

    fun _updateDirectionsObject(
            falconObj: FalconDirectionsModel,
            googleObj: GoogleDirectionsModel
    ): FalconDirectionsModel =
            falconObj.apply {
                geocodeMembers = googleObj.geocodeMembers
                status = googleObj.status
            }

    fun _updateRouteObject(falconObj: FalconRouteModel, googleObj: GoogleRouteModel): FalconRouteModel =
            falconObj.apply {
                bounds = googleObj.bounds
                copyrights = googleObj.copyrights
                legs = googleObj.legs
                overviewPolyline = googleObj.overviewPolyline
                summary = googleObj.summary
                warnings = googleObj.warnings
                waypointOrder = googleObj.waypointOrder
            }

    fun getCustomRoute(origin: Coordinates, destination: Coordinates, bikeStands: List<BikeStandModel>?): List<FalconDirectionsModel?> {


        var res = mutableListOf<FalconDirectionsModel>()

        res = getMultiModeRoute("${origin.latitude},${origin.longitude}",
                "${destination.latitude},${destination.longitude}").toMutableList() as MutableList<FalconDirectionsModel>

//        val geoHashOrgin_Eight = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, 8)
//        val geoHashOrgin_Seven = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, 7)
//        val geoHashOrgin_Six = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, 6)
//        val geoHashOrgin_Five = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, 5)

//        var geoHashOrgin: List<GeoHash> = arrayListOf()
//        var geoHashDestination: List<GeoHash> = arrayListOf()
//
//        for (i in 8 downTo 5) {
//            geoHashOrgin.plus(GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, i))
//            geoHashDestination.plus(GeoHash.withCharacterPrecision(destination.latitude, destination.longitude, i))
//        }

        var found_geoHashOrigin: Boolean = false
        var found_geoHash = false
        for (j in 8 downTo 5) {
            for (i in bikeStands!!.indices) {

                val geoHash = GeoHash.withCharacterPrecision(bikeStands.get(i).standGeoLocation!!.latitude, bikeStands[i].standGeoLocation!!.longitude, j)
                val geoHashOrgin = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, j)

                if (geoHash.equals(geoHashOrgin)) {
                    found_geoHashOrigin = true
                    bikeStandOrgin = bikeStands[i]
                    break
                }
            }

            if (found_geoHashOrigin) {
                break
            }
        }

        for (j in 8 downTo 5) {
            for (i in bikeStands!!.indices) {

                val geoHash = GeoHash.withCharacterPrecision(bikeStands.get(i).standGeoLocation!!.latitude, bikeStands[i].standGeoLocation!!.longitude, j)
                val geoHashDestination = GeoHash.withCharacterPrecision(destination.latitude, destination.longitude, j)

                if (geoHash.equals(geoHashDestination)) {
                    found_geoHash = true
                    bikeStandDestination = bikeStands[i]
                    break
                }
            }

            if (found_geoHash) {
                break
            }
        }

//        var resmodel = FalconDirectionsModel()

        if (::bikeStandOrgin.isInitialized && ::bikeStandDestination.isInitialized && found_geoHash && found_geoHashOrigin) {

            val directionsFromGoogleOrigin = getRouteByEndpointsAndMode("${origin.latitude},${origin.longitude}",
                    "${bikeStandOrgin.standGeoLocation?.latitude},${bikeStandOrgin.standGeoLocation?.longitude}", "walking")
            val directionsFromGoogleCycling = getRouteByEndpointsAndMode("${bikeStandOrgin.standGeoLocation!!.latitude},${bikeStandOrgin.standGeoLocation!!.longitude}",
                    "${bikeStandDestination.standGeoLocation?.latitude},${bikeStandDestination.standGeoLocation?.longitude}", "bicycling")
            val directionsFromGoogleDestination = getRouteByEndpointsAndMode("${bikeStandDestination.standGeoLocation?.latitude},${bikeStandDestination.standGeoLocation?.longitude}",
                    "${destination.latitude},${destination.longitude}", "transit")

            var modifiedDirectionsObjectOrigin = FalconDirectionsModel()
            modifiedDirectionsObjectOrigin = _updateDirectionsObject(modifiedDirectionsObjectOrigin, directionsFromGoogleOrigin!!)
            modifiedDirectionsObjectOrigin.routes = mutableListOf()

            var modifiedDirectionsObjectCycling = FalconDirectionsModel()
            modifiedDirectionsObjectCycling = _updateDirectionsObject(modifiedDirectionsObjectCycling, directionsFromGoogleOrigin!!)
            modifiedDirectionsObjectCycling.routes = mutableListOf()

            var modifiedDirectionsObjectDesitination = FalconDirectionsModel()
            modifiedDirectionsObjectDesitination = _updateDirectionsObject(modifiedDirectionsObjectDesitination, directionsFromGoogleOrigin!!)
            modifiedDirectionsObjectDesitination.routes = mutableListOf()

            for (route in directionsFromGoogleOrigin.routes!!) {
                var newRoute = FalconRouteModel()
                newRoute = _updateRouteObject(newRoute, route)
                newRoute.mode = "multimode"
                modifiedDirectionsObjectOrigin.routes?.add(newRoute)
            }

            val routeSize = modifiedDirectionsObjectOrigin.routes?.size as Int

            for (route in directionsFromGoogleCycling!!.routes!!) {
                val listOfLegs = modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs?.toMutableList() as MutableList<Leg>
                listOfLegs.addAll(route.legs as List<Leg>)
                modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs = listOfLegs
            }

            for (route in directionsFromGoogleDestination!!.routes!!) {
                val listOfLegs = modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs?.toMutableList() as MutableList<Leg>
                listOfLegs.addAll(route.legs as List<Leg>)
                modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs = listOfLegs
//                modifiedDirectionsObjectOrigin.routes!![modifiedDirectionsObjectOrigin.routes!!.size - 1].legs?.toMutableList()!!.addAll(newRoute.legs!!)
            }

//            resmodel = modifiedDirectionsObjectOrigin

            res.add(modifiedDirectionsObjectOrigin)

        }


        return res
    }

}