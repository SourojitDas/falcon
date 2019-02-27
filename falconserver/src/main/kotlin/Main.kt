import io.javalin.Javalin
import services.Weather
import models.WeatherModel

data class FalconResponse(val status: Int, val message: WeatherModel)

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)

//    data class ABC(val name: String, val age: Int)
//    val abcObject = ABC(name = "A b c", age = 12)
    app.get("/") { ctx ->
        val weatherService = Weather()
        val res: WeatherModel? = weatherService.getByCoordinates(35, 139)
        ctx.json(res!!)
    }
}
