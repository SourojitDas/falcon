package handlers.tests

import com.beust.klaxon.Klaxon
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import models.bikestand.BikeStandModel
import models.falcon.FalconDirectionsModel
import models.googleMaps.GoogleDirectionsModel
import models.weather.WeatherModel
import org.junit.Test
import services.BikeStand
import services.BikeStandInterface
import services.GoogleRouteInterface
import services.WeatherInterface
import java.io.File
import java.nio.charset.Charset

class Route {
    @Test
    fun `test weather data is of dublin`() {
        val path = System.getProperty("user.dir")
        val weatherMock = mock<WeatherInterface>()
        val weatherFileData = File(path +"/src/main/kotlin/handlers/tests/weather-mock.json").readText(Charset.defaultCharset())
        val parsedWeatherData =  Klaxon().parse<WeatherModel>(weatherFileData)
        whenever(weatherMock.getByCityID("2964574")).thenReturn(parsedWeatherData)
        assert(parsedWeatherData?.name =="Dublin")
    }

    @Test
    fun `test weather data is null`() {
        val path = System.getProperty("user.dir")
        val weatherMock = mock<WeatherInterface>()
        val weatherFileData = File(path +"/src/main/kotlin/handlers/tests/weather-mock.json").readText(Charset.defaultCharset())
        val parsedWeatherData =  Klaxon().parse<WeatherModel>(weatherFileData)
        whenever(weatherMock.getByCityID("2964574")).thenReturn(parsedWeatherData)
        assert(parsedWeatherData?.weather ==null)
    }

    @Test
    fun `test bike data is of dublin`() {
        val path = System.getProperty("user.dir")
        val bikeFileData = File(path+"/src/main/kotlin/handlers/tests/bike-mock.json").readText(Charset.defaultCharset())
        val parsedBikeData = Klaxon().parseArray<BikeStandModel>(bikeFileData)
        val bikeMock = mock<BikeStandInterface>()
        whenever(bikeMock.getRealTimeStandsInfoByCity("Dublin")).thenReturn(parsedBikeData)
        assert(parsedBikeData?.get(0)?.contract_name == "Dublin")
    }

    @Test
    fun `test bike data address is Smithfield North`() {
        val path = System.getProperty("user.dir")
        val bikeFileData =
            File(path + "/src/main/kotlin/handlers/tests/bike-mock.json").readText(Charset.defaultCharset())
        val parsedBikeData = Klaxon().parseArray<BikeStandModel>(bikeFileData)
        val bikeMock = mock<BikeStandInterface>()
        whenever(bikeMock.getRealTimeStandsInfoByCity("Dublin")).thenReturn(parsedBikeData)
        assert(parsedBikeData?.get(0)?.address == "Smithfield North")
    }

    @Test
    fun `test falcon route mode is driving`(){
        val path = System.getProperty("user.dir")
        val fileData = File(path+"/src/main/kotlin/handlers/tests/ListOfFalconDirection.json").readText(Charset.defaultCharset())
        val parsedData = Klaxon().parseArray<FalconDirectionsModel>(fileData)
        val mockGoogleRouteService = mock<GoogleRouteInterface>()
        whenever(mockGoogleRouteService.getMultiModeRoute("53.3436935,-6.259189499999999",
            "53.3415983,-6.2542983")).thenReturn(parsedData)
        assert(parsedData?.get(0)?.routes?.get(0)?.mode=="driving")
    }
}