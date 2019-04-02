package handlers

import io.javalin.Context
import models.falcon.FalconDirectionsModel
import models.falcon.RequestBody
import services.BikeStand
import services.GoogleRoute
import services.Weather

object GoogleRouteController {
    private const val LuasMode = "Luas"
    private const val BusMode = "Bus"
    private const val CarMode = "Car"
    private const val WalkMode = "Walk"
    private const val CycleMode = "Cycle"

    val modeMapping = hashMapOf(
        LuasMode to services.GoogleRoute.TransitMode,
        BusMode to services.GoogleRoute.TransitMode,
        CarMode to services.GoogleRoute.DrivingMode,
        WalkMode to services.GoogleRoute.WalkingMode,
        CycleMode to services.GoogleRoute.BicyclingMode
    )

    fun getRouteByOriginAndDestination(ctx: Context) {
        val body = ctx.body<RequestBody>()
        val res = constructCustomRoute(body)
        ctx.json(res)
    }

    private fun constructCustomRoute(data: RequestBody): models.falcon.FalconDirectionsModel {
        val destination = data.destination!!
        val origin = data.origin!!
        val cityID = data.cityID
        val cityName = data.cityName
        val userPreferences = data.preferences!!

//        val multiModeDirections: List<models.falcon.FalconDirectionsModel?> = GoogleRoute.getMultiModeRoute(
//            "${origin.latitude},${origin.longitude}",
//            "${destination.latitude},${destination.longitude}"
//        )


        val bikeStands = BikeStand.getRealTimeStandsInfoByCity(cityName)

        val multiModeDirections =  GoogleRoute.getCustomRoute(origin,destination,bikeStands)

        val weather = Weather.getByCityID(cityID)


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