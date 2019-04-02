package models.tests

import models.weather.Rain
import models.weather.parseJson
import org.junit.Test

class WeatherTest {
    val fakeWeatherData = """{
            "coord":
            {"lon":145.77,"lat":-16.92},
            "weather":[{
                "id":803,
                "main":"Clouds",
                "description":"broken clouds",
                "icon":"04n"
            }],
            "base":"cmc stations",
            "main":{
                "temp":293.25,
                "pressure":1019,
                "humidity":83,
                "temp_min":289.82,
                "temp_max":295.37
            },
            "wind":{"speed":5.1,"deg":150},
            "clouds":{"all":75},
            "rain":{"3h":3},
            "dt":1435658272,
            "sys":{
                "type":1,
                "id":8166,
                "message":0.0166,
                "country":"AU",
                "sunrise":1435610796,
                "sunset":1435650870
            },
            "id":2172797,
            "name":"Cairns",
            "cod":200
        }"""

    @Test
    fun `test parseJson success`() {
        val result = parseJson(fakeWeatherData)
        assert(result?.name == "Cairns")
        val fakeId: Long = 2172797
        assert(result?.ID == fakeId)
        val fakeCod:  Int= 200
        assert(result?.cod == fakeCod)
        val fakeDt = 1435658272
        assert(result?.date?.equals(fakeDt)!!)
        val fakeRain = Rain(lastThreeHour = 3)
        assert(result?.rain?.lastThreeHour == fakeRain.lastThreeHour)
        val fakeWeather =
            models.weather.WeatherInfo(id = 803, main = "Clouds", description = "broken clouds", icon = "04n")
        assert(result?.weather?.first()?.id == fakeWeather.id)
        assert(result?.weather?.first()?.main.equals(fakeWeather.main))
        assert(result?.weather?.first()?.description.equals(fakeWeather.description))
        assert(result?.weather?.first()?.icon.equals(fakeWeather.icon))
        val fakeBase = "cmc stations"
        assert(result?.base.equals(fakeBase))
        val fakeMain = models.weather.MainTemperature(
            temperature = 293.25F,
            pressure = 1019,
            humidity = 83,
            tempMin = 289.82F,
            tempMax = 295.37F
        )
        assert(result?.mainTemperature?.temperature == fakeMain.temperature)
        assert(result?.mainTemperature?.pressure == fakeMain.pressure)
        assert(result?.mainTemperature?.tempMin == fakeMain.tempMin)
        assert(result?.mainTemperature?.tempMax == fakeMain.tempMax)
        assert(result?.mainTemperature?.humidity == fakeMain.humidity)
        val fakeWind = models.weather.Wind(speed = 5.1F, degree = 150F)
        assert(result?.wind?.speed == fakeWind.speed)
        assert(result?.wind?.degree == fakeWind.degree)
        val fakeCloud = models.weather.Clouds(all = 75)
        assert(result?.clouds?.all == fakeCloud.all)
        val fakeCoord = models.weather.Coordinates(longitude = 145.77, latitude = -16.92)
        assert(result?.coordinates?.latitude == fakeCoord.latitude)
        assert(result?.coordinates?.latitude == fakeCoord.longitude)
        val fakeSystemInfo = models.weather.SystemInfo(
            type = 1,
            id = 8166,
            message = 0.0166F,
            country = "AU",
            sunrise = 1435610796,
            sunset = 1435650870
        )
        assert(result?.systemInfo?.sunrise?.equals(fakeSystemInfo.sunrise)!!)
        assert(result?.systemInfo?.sunset?.equals(fakeSystemInfo.sunset)!!)
        assert(result?.systemInfo?.type?.equals(fakeSystemInfo.type)!!)
        assert(result?.systemInfo?.id?.equals(fakeSystemInfo.id)!!)
        assert(result?.systemInfo?.country.equals(fakeSystemInfo.country))
        assert(result?.systemInfo?.message == fakeSystemInfo.message)

    }


    @Test
    fun `test parseJson success1`() {
        val result = parseJson(fakeWeatherData)
        assert(result?.name == "Cairns")
        val fakeId: Long = 2172796
        assert(result?.ID == fakeId)
        val fakeCod:  Int= 200
        assert(result?.cod == fakeCod)
        val fakeDt = 1435658272
        assert(result?.date?.equals(fakeDt)!!)
        val fakeRain = Rain(lastThreeHour = 3)
        assert(result?.rain?.lastThreeHour == fakeRain.lastThreeHour)
        val fakeWeather =
            models.weather.WeatherInfo(id = 803, main = "Clouds", description = "broken clouds", icon = "04n")
        assert(result?.weather?.first()?.id == fakeWeather.id)
        assert(result?.weather?.first()?.main.equals(fakeWeather.main))
        assert(result?.weather?.first()?.description.equals(fakeWeather.description))
        assert(result?.weather?.first()?.icon.equals(fakeWeather.icon))
        val fakeBase = "cmc stations"
        assert(result?.base.equals(fakeBase))
        val fakeMain = models.weather.MainTemperature(
            temperature = 293.25F,
            pressure = 1019,
            humidity = 83,
            tempMin = 289.82F,
            tempMax = 295.37F
        )
        assert(result?.mainTemperature?.temperature == fakeMain.temperature)
        assert(result?.mainTemperature?.pressure == fakeMain.pressure)
        assert(result?.mainTemperature?.tempMin == fakeMain.tempMin)
        assert(result?.mainTemperature?.tempMax == fakeMain.tempMax)
        assert(result?.mainTemperature?.humidity == fakeMain.humidity)
        val fakeWind = models.weather.Wind(speed = 5.1F, degree = 150F)
        assert(result?.wind?.speed == fakeWind.speed)
        assert(result?.wind?.degree == fakeWind.degree)
        val fakeCloud = models.weather.Clouds(all = 75)
        assert(result?.clouds?.all == fakeCloud.all)
        val fakeCoord = models.weather.Coordinates(longitude = 145.77F as Double, latitude = -16.92F as Double)
        assert(result?.coordinates?.latitude == fakeCoord.latitude)
        assert(result?.coordinates?.latitude == fakeCoord.longitude)
        val fakeSystemInfo = models.weather.SystemInfo(
            type = 1,
            id = 8166,
            message = 0.0166F,
            country = "AU",
            sunrise = 1435610796,
            sunset = 1435650870
        )
        assert(result?.systemInfo?.sunrise?.equals(fakeSystemInfo.sunrise)!!)
        assert(result?.systemInfo?.sunset?.equals(fakeSystemInfo.sunset)!!)
        assert(result?.systemInfo?.type?.equals(fakeSystemInfo.type)!!)
        assert(result?.systemInfo?.id?.equals(fakeSystemInfo.id)!!)
        assert(result?.systemInfo?.country.equals(fakeSystemInfo.country))
        assert(result?.systemInfo?.message == fakeSystemInfo.message)

    }


    @Test
    fun `test parseJson success3`() {
        val result = parseJson(fakeWeatherData)
        assert(result?.name == "Cairns")
        val fakeId: Long = 2172797
        assert(result?.ID == fakeId)
        val fakeCod:  Int= 200
        assert(result?.cod == fakeCod)
        val fakeDt = 1435658277
        assert(result?.date?.equals(fakeDt)!!)
        val fakeRain = Rain(lastThreeHour = 3)
        assert(result?.rain?.lastThreeHour == fakeRain.lastThreeHour)
        val fakeWeather =
            models.weather.WeatherInfo(id = 803, main = "Clouds", description = "broken clouds", icon = "04n")
        assert(result?.weather?.first()?.id == fakeWeather.id)
        assert(result?.weather?.first()?.main.equals(fakeWeather.main))
        assert(result?.weather?.first()?.description.equals(fakeWeather.description))
        assert(result?.weather?.first()?.icon.equals(fakeWeather.icon))
        val fakeBase = "cmc stations"
        assert(result?.base.equals(fakeBase))
        val fakeMain = models.weather.MainTemperature(
            temperature = 293.25F,
            pressure = 1019,
            humidity = 83,
            tempMin = 289.82F,
            tempMax = 295.37F
        )
        assert(result?.mainTemperature?.temperature == fakeMain.temperature)
        assert(result?.mainTemperature?.pressure == fakeMain.pressure)
        assert(result?.mainTemperature?.tempMin == fakeMain.tempMin)
        assert(result?.mainTemperature?.tempMax == fakeMain.tempMax)
        assert(result?.mainTemperature?.humidity == fakeMain.humidity)
        val fakeWind = models.weather.Wind(speed = 5.1F, degree = 150F)
        assert(result?.wind?.speed == fakeWind.speed)
        assert(result?.wind?.degree == fakeWind.degree)
        val fakeCloud = models.weather.Clouds(all = 75)
        assert(result?.clouds?.all == fakeCloud.all)
        val fakeCoord = models.weather.Coordinates(longitude = 145.77, latitude = -16.92)
        assert(result?.coordinates?.latitude == fakeCoord.latitude)
        assert(result?.coordinates?.latitude == fakeCoord.longitude)
        val fakeSystemInfo = models.weather.SystemInfo(
            type = 1,
            id = 8166,
            message = 0.0166F,
            country = "AU",
            sunrise = 1435610796,
            sunset = 1435650870
        )
        assert(result?.systemInfo?.sunrise?.equals(fakeSystemInfo.sunrise)!!)
        assert(result?.systemInfo?.sunset?.equals(fakeSystemInfo.sunset)!!)
        assert(result?.systemInfo?.type?.equals(fakeSystemInfo.type)!!)
        assert(result?.systemInfo?.id?.equals(fakeSystemInfo.id)!!)
        assert(result?.systemInfo?.country.equals(fakeSystemInfo.country))
        assert(result?.systemInfo?.message == fakeSystemInfo.message)

    }

    @Test
    fun `test parseJson success4`() {
        val result = parseJson(fakeWeatherData)
        assert(result?.name == "Cairns")
        val fakeId: Long = 2172797
        assert(result?.ID == fakeId)
        val fakeCod: Int= 20
        assert(result?.cod == fakeCod)
        val fakeDt = 1435658272
        assert(result?.date?.equals(fakeDt)!!)
        val fakeRain = Rain(lastThreeHour = 3)
        assert(result?.rain?.lastThreeHour == fakeRain.lastThreeHour)
        val fakeWeather =
            models.weather.WeatherInfo(id = 803, main = "Clouds", description = "broken clouds", icon = "04n")
        assert(result?.weather?.first()?.id == fakeWeather.id)
        assert(result?.weather?.first()?.main.equals(fakeWeather.main))
        assert(result?.weather?.first()?.description.equals(fakeWeather.description))
        assert(result?.weather?.first()?.icon.equals(fakeWeather.icon))
        val fakeBase = "cmc stations"
        assert(result?.base.equals(fakeBase))
        val fakeMain = models.weather.MainTemperature(
            temperature = 293.25F,
            pressure = 1019,
            humidity = 83,
            tempMin = 289.82F,
            tempMax = 295.37F
        )
        assert(result?.mainTemperature?.temperature == fakeMain.temperature)
        assert(result?.mainTemperature?.pressure == fakeMain.pressure)
        assert(result?.mainTemperature?.tempMin == fakeMain.tempMin)
        assert(result?.mainTemperature?.tempMax == fakeMain.tempMax)
        assert(result?.mainTemperature?.humidity == fakeMain.humidity)
        val fakeWind = models.weather.Wind(speed = 5.1F, degree = 150F)
        assert(result?.wind?.speed == fakeWind.speed)
        assert(result?.wind?.degree == fakeWind.degree)
        val fakeCloud = models.weather.Clouds(all = 75)
        assert(result?.clouds?.all == fakeCloud.all)
        val fakeCoord = models.weather.Coordinates(longitude = 145.77, latitude = -16.92)
        assert(result?.coordinates?.latitude == fakeCoord.latitude)
        assert(result?.coordinates?.latitude == fakeCoord.longitude)
        val fakeSystemInfo = models.weather.SystemInfo(
            type = 1,
            id = 8166,
            message = 0.0166F,
            country = "AU",
            sunrise = 1435610796,
            sunset = 1435650870
        )
        assert(result?.systemInfo?.sunrise?.equals(fakeSystemInfo.sunrise)!!)
        assert(result?.systemInfo?.sunset?.equals(fakeSystemInfo.sunset)!!)
        assert(result?.systemInfo?.type?.equals(fakeSystemInfo.type)!!)
        assert(result?.systemInfo?.id?.equals(fakeSystemInfo.id)!!)
        assert(result?.systemInfo?.country.equals(fakeSystemInfo.country))
        assert(result?.systemInfo?.message == fakeSystemInfo.message)

    }

    @Test
    fun `test parseJson success6`() {
        val result = parseJson(fakeWeatherData)
        assert(result?.name == "Cairns")
        val fakeId: Long = 2172797
        assert(result?.ID == fakeId)
        val fakeCod:  Int= 200
        assert(result?.cod == fakeCod)
        val fakeDt = 1435658272
        assert(result?.date?.equals(fakeDt)!!)
        val fakeRain = Rain(lastThreeHour = 3)
        assert(result?.rain?.lastThreeHour == fakeRain.lastThreeHour)
        val fakeWeather =
            models.weather.WeatherInfo(id = 803, main = "Clouds", description = "broken clouds", icon = "04n")
        assert(result?.weather?.first()?.id == fakeWeather.id)
        assert(result?.weather?.first()?.main.equals(fakeWeather.main))
        assert(result?.weather?.first()?.description.equals(fakeWeather.description))
        assert(result?.weather?.first()?.icon.equals(fakeWeather.icon))
        val fakeBase = "cmc stations"
        assert(result?.base.equals(fakeBase))
        val fakeMain = models.weather.MainTemperature(
            temperature = 23.25F,
            pressure = 1019,
            humidity = 83,
            tempMin = 289.82F,
            tempMax = 295.37F
        )
        assert(result?.mainTemperature?.temperature == fakeMain.temperature)
        assert(result?.mainTemperature?.pressure == fakeMain.pressure)
        assert(result?.mainTemperature?.tempMin == fakeMain.tempMin)
        assert(result?.mainTemperature?.tempMax == fakeMain.tempMax)
        assert(result?.mainTemperature?.humidity == fakeMain.humidity)
        val fakeWind = models.weather.Wind(speed = 5.1F, degree = 150F)
        assert(result?.wind?.speed == fakeWind.speed)
        assert(result?.wind?.degree == fakeWind.degree)
        val fakeCloud = models.weather.Clouds(all = 75)
        assert(result?.clouds?.all == fakeCloud.all)
        val fakeCoord = models.weather.Coordinates(longitude = 145.77, latitude = -16.92)
        assert(result?.coordinates?.latitude == fakeCoord.latitude)
        assert(result?.coordinates?.latitude == fakeCoord.longitude)
        val fakeSystemInfo = models.weather.SystemInfo(
            type = 1,
            id = 8166,
            message = 0.0166F,
            country = "AU",
            sunrise = 1435610796,
            sunset = 1435650870
        )
        assert(result?.systemInfo?.sunrise?.equals(fakeSystemInfo.sunrise)!!)
        assert(result?.systemInfo?.sunset?.equals(fakeSystemInfo.sunset)!!)
        assert(result?.systemInfo?.type?.equals(fakeSystemInfo.type)!!)
        assert(result?.systemInfo?.id?.equals(fakeSystemInfo.id)!!)
        assert(result?.systemInfo?.country.equals(fakeSystemInfo.country))
        assert(result?.systemInfo?.message == fakeSystemInfo.message)

    }
}