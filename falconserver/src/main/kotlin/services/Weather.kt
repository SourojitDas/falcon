package services

import Configuration
import models.weather.Coordinates
import models.weather.parseJson


object Weather {
    fun getByCoordinates(coordinates: Coordinates): models.weather.WeatherModel? {
        val payload = mapOf(
            "lat" to coordinates.latitude.toString(),
            "lon" to coordinates.longitude.toString(),
            "appid" to Configuration.getWeatherServiceApiKey()
        )
        val r = khttp.get(Configuration.getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }

    fun getByCityID(cityID: String): models.weather.WeatherModel? {
        val payload = mapOf(
            "id" to cityID,
            "appid" to Configuration.getWeatherServiceApiKey()
        )
        val r = khttp.get(Configuration.getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}