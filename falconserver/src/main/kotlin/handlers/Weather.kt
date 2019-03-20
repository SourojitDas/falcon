package handlers

import io.javalin.Context
import models.weather.WeatherModel
import services.Weather

object WeatherController {
    fun getWeatherByCoordinates(ctx: Context) {
        val weatherService = Weather()
        val res = weatherService.getByCoordinates(35, 139)
        ctx.json(res!!)
    }
}