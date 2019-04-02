package handlers

import io.javalin.Context
import models.falcon.RequestBody
import models.weather.Coordinates
import services.Weather

object WeatherController {
    fun getWeatherByCoordinates(ctx: Context) {
        val body = ctx.body<RequestBody>()
        val res = Weather.getByCoordinates(body.origin as Coordinates)
        ctx.json(res!!)
    }

    fun getWeatherByCityID(ctx: Context) {
        val body = ctx.body<RequestBody>()
        val res = Weather.getByCityID(body.cityID)
        ctx.json(res!!)
    }
}