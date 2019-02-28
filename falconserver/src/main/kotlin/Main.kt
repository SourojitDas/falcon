import io.javalin.Javalin
import services.Weather
import models.weather.WeatherModel
import services.BikeStand

//data class FalconResponse(val status: Int, val message: WeatherModel)

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)

    app.get("/weather") { ctx ->
        val weatherService = Weather()
        val res: WeatherModel? = weatherService.getByCoordinates(35, 139)
        ctx.json(res!!)
    }
    app.get("/bikestand") { ctx ->
        val bikeStandService = BikeStand()
        val res = bikeStandService.getRealTimeStandsInfoByCity("Dublin")
        ctx.json(res!!)
    }
}
