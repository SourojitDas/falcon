import handlers.ApiRole
import io.javalin.Javalin
import handlers.*
import io.javalin.security.SecurityUtil.roles


fun main(args: Array<String>) {
    val app = Javalin.create().apply {
        accessManager(Authorization::accessManager)
    }.start(7000)

    app.get("/weather", WeatherController::getWeatherByCoordinates, roles(ApiRole.USER_READ))
    app.get("/bikestand", BikeStandController::getRealTimeStandsInfoByCity, roles(ApiRole.USER_READ))
}