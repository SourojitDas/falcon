package services

import models.googleMaps.parseJson

val DrivingMode = "driving"
val WalkingMode = "walking"
val BicyclingMode = "bicycling"
val TransitMode = "transit"

class GoogleRoute {
    fun getRouteByEndpointsAndMode(origin: String, destination: String, mode: String):  models.googleMaps.GoogleRouteModel? {
        val payload = mapOf(
            "origin" to origin,
            "destination" to destination,
            "mode" to mode,
            "key" to Configuration.getGoogleMapsServiceApiKey()
        )
        val r = khttp.get(Configuration.getGoogleMapsServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}