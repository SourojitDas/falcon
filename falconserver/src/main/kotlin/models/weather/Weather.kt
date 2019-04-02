package models.weather

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

data class Coordinates(
    @Json(name = "lon")
    override val longitude: Double,
    @Json(name = "lat")
    override val latitude: Double
) : models.falcon.CoordinatesModel

data class Rain(
    @Json(name = "1h")
    val lastOneHour: Float? = null,
    @Json(name = "3h")
    val lastThreeHour: Float? = null
)

data class WeatherInfo(
        val id: Int? = null,
        @Json(name = "main")
        val main: String? = null,
        val description: String? = null,
        val icon: String? = null
)

data class MainTemperature(
        @Json(name = "temp")
        val temperature: Float? = null,
        val pressure: Int? = null,
        val humidity: Int? = null,
        @Json(name = "temp_min")
        val tempMin: Float? = null,
        @Json(name = "temp_max")
        val tempMax: Float? = null,
        @Json(name = "sea_level")
        val seaLevel: Float? = null,
        @Json(name = "grnd_level")
        val groundLevel: Float? = null
)

data class Snow(
        @Json(name = "1h")
        val LastOneHour: Float? = null,
        @Json(name = "3h")
        val LastThreeHour: Float? = null
)

data class Wind(
        val speed: Float? = null,
        @Json(name = "deg")
        val degree: Float? = null,
        val gust: Float? = null
)

data class Clouds(
        val all: Int? = null
)

data class SystemInfo(
        val type: Int? = null,
        val id: Long? = null,
        val message: Float? = null,
        val country: String? = null,
        val sunrise: Long? = null,
        val sunset: Long? = null
)

data class WeatherModel(
        @Json(name = "coord")
        val coordinates: Coordinates? = null,
        @Json(name = "services")
        val weather: List<WeatherInfo>? = null,
        val base: String? = null,
        @Json(name = "main")
        val mainTemperature: MainTemperature? = null,
        val visibility: Long? = null,
        @Json(name = "wind")
        val wind: Wind? = null,
        @Json(name = "clouds")
        val clouds: Clouds? = null,
        @Json(name = "dt")
        val date: Long? = null,
        @Json(name = "sys")
        val systemInfo: SystemInfo? = null,
        @Json(name = "id")
        val ID: Long? = null,
        val name: String? = null,
        val cod: Int? = null,
        val rain: Rain? = null,
        val snow: Snow? = null
)

fun parseJson(raw: String): WeatherModel? {
    return Klaxon().parse<WeatherModel>(raw)
}