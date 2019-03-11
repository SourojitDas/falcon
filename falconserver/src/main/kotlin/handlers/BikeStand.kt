package handlers

import io.javalin.Context
import services.BikeStand

object BikeStandController {
    fun getRealTimeStandsInfoByCity(ctx: Context) {
        val bikeStandService = BikeStand()
        val res = bikeStandService.getRealTimeStandsInfoByCity("Dublin")
        ctx.json(res!!)
    }
}