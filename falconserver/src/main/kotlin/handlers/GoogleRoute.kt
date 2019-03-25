package handlers

import io.javalin.Context
import services.GoogleRoute

object GoogleRouteController {
    fun getRouteByOriginAndDestination(ctx: Context) {
        val googleRouteService = GoogleRoute()
        val res = googleRouteService.getRouteByOriginAndDestination("Trinity College Dublin", "UCD Belfield")
        ctx.json(res!!)
    }
}