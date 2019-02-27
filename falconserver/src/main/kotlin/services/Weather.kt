package services

import getWeatherServiceApiKey
import getWeatherServiceBaseURL
import models.parseJson


class Weather {
    fun getByCoordinates(latitude: Int, longitude: Int): models.WeatherModel? {
        val payload = mapOf(
                "lat" to latitude.toString(),
                "lon" to longitude.toString(),
                "appid" to getWeatherServiceApiKey()
        )
        val r = khttp.get(getWeatherServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}