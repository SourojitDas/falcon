package handlers

import io.javalin.Context
import models.falcon.FalconDirectionsModel
import models.falcon.RequestBody
import services.BikeStand
import services.GoogleRoute
import services.Weather

object GoogleRouteController {
    fun getRouteByOriginAndDestination(ctx: Context) {
        val body = ctx.body<RequestBody>()
        val res = constructCustomRoute(body)
        ctx.json(res)
    }

    private fun constructCustomRoute(data: RequestBody): models.falcon.FalconDirectionsModel {
        val googleRouteService = GoogleRoute()
        val weatherService = Weather()
        val bikeStandService = BikeStand()

        val destination = data.destination!!
        val origin = data.origin!!
        val cityID = data.cityID
        val cityName = data.cityName
        val userPreferences = data.preferences!!

        val multiModeDirections: List<models.falcon.FalconDirectionsModel?> = googleRouteService.getMultiModeRoute(
            "${origin.latitude},${origin.longitude}",
            "${destination.latitude},${destination.longitude}"
        )

        val weather = weatherService.getByCityID(cityID)
        val bikeStands = bikeStandService.getRealTimeStandsInfoByCity(cityName)

        val res: FalconDirectionsModel? = FalconDirectionsModel()
        res?.routes = mutableListOf()
        res?.geocodeMembers = multiModeDirections[0]?.geocodeMembers
        res?.status = multiModeDirections[0]?.status
        res?.weather = weather
        res?.bikeStands = bikeStands


        for (routeObject in multiModeDirections) {
            res?.routes!!.addAll(routeObject!!.routes!!)
        }
        return res!!
    }
}