package services

import Configuration
import models.weather.Coordinates
import models.weather.parseJson


object Weather: WeatherInterface{
    override fun getByCoordinates(coordinates: Coordinates): models.weather.WeatherModel? {
        val payload = mapOf(
            "lat" to coordinates.latitude.toString(),
            "lon" to coordinates.longitude.toString(),
            "appid" to Configuration.getWeatherServiceApiKey()
        )
        val r = khttp.get(Configuration.getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }

    override fun getByCityID(cityID: String): models.weather.WeatherModel? {
        val payload = mapOf(
            "id" to cityID,
            "appid" to Configuration.getWeatherServiceApiKey()
        )
        val r = khttp.get(Configuration.getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}

interface WeatherInterface {
    fun getByCoordinates(coordinates: Coordinates): models.weather.WeatherModel?
    fun getByCityID(cityID: String): models.weather.WeatherModel?
}