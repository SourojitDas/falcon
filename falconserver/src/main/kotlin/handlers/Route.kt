package handlers

import io.javalin.Context
import models.falcon.Coordinates
import models.falcon.FalconDirectionsModel
import models.falcon.RequestBody
import services.*

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
        val res = constructCustomRoute(body, Weather, BikeStand, GoogleRoute, Event)
        ctx.json(res)
    }

    private fun constructCustomRoute(data: RequestBody, weatherInterface: WeatherInterface,
                                     bikeStandInterface: BikeStandInterface,
                                     googleRouteInterface: GoogleRouteInterface, eventInterface: EventInterface): models.falcon.FalconDirectionsModel {
        val destination = data.destination as Coordinates
        val origin = data.origin!!
        val cityID = data.cityID
        val cityName = data.cityName
        val countryName = "IE"
        val userPreferences = data.preferences!!

//        val multiModeDirections: List<models.falcon.FalconDirectionsModel?> = googleRouteInterface.getMultiModeRoute(
//            "${origin.latitude},${origin.longitude}",
//            "${destination?.latitude},${destination?.longitude}"
//        )

        val weather = weatherInterface.getByCityID(cityID)
        val bikeStands = bikeStandInterface.getRealTimeStandsInfoByCity(cityName)
        val multiModeDirections =  GoogleRoute.getCustomRoute(origin,destination,bikeStands)
//        val events = eventInterface.getRealTimeEventsInfoByPlace(countryName)

        val res: FalconDirectionsModel? = FalconDirectionsModel()
        res?.routes = mutableListOf()
        res?.geocodeMembers = multiModeDirections[0]?.geocodeMembers
        res?.status = multiModeDirections[0]?.status
        res?.weather = weather
        res?.bikeStands = bikeStands
//        res?.events = events


        for (routeObject in multiModeDirections) {
            res?.routes!!.addAll(routeObject!!.routes!!)
        }
        return res!!
    }
}