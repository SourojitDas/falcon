package handlers

import io.javalin.Context
import models.googleMaps.Coordinates
import models.googleMaps.GoogleRouteModel
import models.googleMaps.Route
import services.*

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
        val drivingRouteFromGoogle = googleRouteService.getRouteByEndpointsAndMode("${body.origin.latitude},${body.origin.longitude}",
            "${body.destination.latitude},${body.destination.longitude}",
            DrivingMode)
        val walkingRouteFromGoogle = googleRouteService.getRouteByEndpointsAndMode("${body.origin.latitude},${body.origin.longitude}",
            "${body.destination.latitude},${body.destination.longitude}",
            WalkingMode)
        val bicylingRouteFromGoogle = googleRouteService.getRouteByEndpointsAndMode("${body.origin.latitude},${body.origin.longitude}",
            "${body.destination.latitude},${body.destination.longitude}",
            BicyclingMode)
        val transitRouteFromGoogle = googleRouteService.getRouteByEndpointsAndMode("${body.origin.latitude},${body.origin.longitude}",
            "${body.destination.latitude},${body.destination.longitude}",
            TransitMode)
        val listOfRoutes = listOf(walkingRouteFromGoogle, drivingRouteFromGoogle, bicylingRouteFromGoogle, transitRouteFromGoogle)
        val res = constructCustomRoute(body.preferences, listOfRoutes)
        ctx.json(res)
    }

    private fun constructCustomRoute(userPreferences: List<Preferences>, routesFromGoogle: List<models.googleMaps.GoogleRouteModel?>):models.googleMaps.GoogleRouteModel {
        val res : GoogleRouteModel? = GoogleRouteModel()
        res?.routes = mutableListOf()
        res?.geocodeMembers = routesFromGoogle.get(0)?.geocodeMembers
        res?.status = routesFromGoogle.get(0)?.status
        for(routeObject in routesFromGoogle) {
//            println (routeObject?.geocodeMembers)
//            println (routeObject?.routes!!.size)
            res?.routes!!.addAll(routeObject!!.routes!!)
        }
        return res!!
    }
}