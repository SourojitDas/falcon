package handlers

import io.javalin.Context
import models.falcon.RequestBody
import services.BikeStand

object BikeStandController {
    fun getRealTimeStandsInfoByCity(ctx: Context) {
        val bikeStandService = BikeStand()
        val body = ctx.body<RequestBody>()
        val res = bikeStandService.getRealTimeStandsInfoByCity(body.cityName)
        ctx.json(res!!)
    }
}