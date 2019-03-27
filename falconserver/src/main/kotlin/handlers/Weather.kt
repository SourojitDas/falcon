package handlers

import io.javalin.Context
import models.falcon.RequestBody
import models.weather.Coordinates
import services.Weather

object WeatherController {
    fun getWeatherByCoordinates(ctx: Context) {
        val weatherService = Weather()
        val body = ctx.body<RequestBody>()
        val res = weatherService.getByCoordinates(body.origin as Coordinates)
        ctx.json(res!!)
    }

    fun getWeatherByCityID(ctx: Context) {
        val weatherService = Weather()
        val body = ctx.body<RequestBody>()
        val res = weatherService.getByCityID(body.cityID)
        ctx.json(res!!)
    }
}