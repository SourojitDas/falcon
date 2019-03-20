package services

import models.googleMaps.parseJson


class GoogleRoute {
    fun getRouteByOriginAndDestination(origin: String, destination: String):  models.googleMaps.GoogleRouteModel? {
        val payload = mapOf(
            "origin" to origin,
            "destination" to destination,
            "key" to Configuration.getGoogleMapsServiceApiKey()
        )
        val r = khttp.get(Configuration.getGoogleMapsServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}