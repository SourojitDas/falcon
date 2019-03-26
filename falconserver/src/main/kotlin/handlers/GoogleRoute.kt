package handlers

import com.sun.org.apache.xpath.internal.operations.Bool
import io.javalin.Context
import models.googleMaps.Coordinates
import services.GoogleRoute

data class Preferences (
    val mode: String,
    val selected: Boolean
)

data class Body (
    val preferences: List<Preferences>,
    val origin: Coordinates,
    val destination: Coordinates
)

object GoogleRouteController {
    fun getRouteByOriginAndDestination(ctx: Context) {
        val googleRouteService = GoogleRoute()
        val body = ctx.body<Body>()
        val routeFromGoogle = googleRouteService.getRouteByOriginAndDestination("${body.origin.latitude},${body.origin.longitude}", "${body.destination.latitude},${body.destination.longitude}")
        val res = constructCustomRoute(body.preferences, routeFromGoogle!!)
        ctx.json(res)
    }

    private fun constructCustomRoute(userPreferences: List<Preferences>, routeFromGoogle: models.googleMaps.GoogleRouteModel):models.googleMaps.GoogleRouteModel {

        return routeFromGoogle
    }
}