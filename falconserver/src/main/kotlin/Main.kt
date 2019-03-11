import handlers.ApiRole
import io.javalin.Javalin
import services.Weather
import models.weather.WeatherModel
import services.BikeStand
import handlers.Authentication
import handlers.BikeStandController
import handlers.WeatherController
import io.javalin.security.SecurityUtil.roles

//data class FalconResponse(val status: Int, val message: WeatherModel)

fun main(args: Array<String>) {
//    val app = Javalin.create().start(7000)


    val app = Javalin.create().apply {
        accessManager(Authentication::accessManager)
    }.start(7000)

    app.get("/weather", WeatherController::getWeatherByCoordinates, roles(ApiRole.USER_READ))
    app.get("/bikestand", BikeStandController::getRealTimeStandsInfoByCity, roles(ApiRole.USER_READ))

}