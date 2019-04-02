package services

import Configuration
import models.falcon.FalconDirectionsModel
import models.falcon.FalconRouteModel
import models.googleMaps.GoogleDirectionsModel
import models.googleMaps.GoogleRouteModel
import models.googleMaps.parseJson

object GoogleRoute {
    val DrivingMode = "driving"
    val WalkingMode = "walking"
    val BicyclingMode = "bicycling"
    val TransitMode = "transit"

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

}