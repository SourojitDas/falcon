package services

import getWeatherServiceApiKey
import getWeatherServiceBaseURL
import models.weather.parseJson


class Weather {
    fun getByCoordinates(latitude: Int, longitude: Int): models.weather.WeatherModel? {
        val payload = mapOf(
                "lat" to latitude.toString(),
                "lon" to longitude.toString(),
                "appid" to getWeatherServiceApiKey()
        )
        val r = khttp.get(getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}