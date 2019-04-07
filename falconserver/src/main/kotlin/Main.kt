import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import handlers.ApiRole
import handlers.Authorization
import handlers.GoogleRouteController
import handlers.PingController
import io.javalin.Javalin
import io.javalin.security.SecurityUtil.roles


fun main(/*args: Array<String>*/) {

    bootstrapFirebaseSDK()
    val server = createServer()
    bootstrapRoutes(server)

}

fun bootstrapRoutes(server: Javalin) {
//    server.get("/weather", WeatherController::getWeatherByCoordinates, roles(ApiRole.USER_READ))
//    server.get("/bikestand", BikeStandController::getRealTimeStandsInfoByCity, roles(ApiRole.USER_READ))
    server.get("/___p_i_n_g", PingController::pong, roles(ApiRole.USER_READ))
    server.post("/route", GoogleRouteController::getRouteByOriginAndDestination, roles(ApiRole.USER_READ))
}

fun bootstrapFirebaseSDK() {
    val classloader = Thread.currentThread().contextClassLoader
    val serviceAccount = classloader.getResourceAsStream("config/falcon-a0154-c1fb6052a19c.json")
    //FileInputStream("config/falcon-a0154-c1fb6052a19c.json")

    val options = FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://falcon-a0154.firebaseio.com")
        .build()

    FirebaseApp.initializeApp(options)
}

fun createServer(): Javalin {
    return Javalin.create().apply {
        accessManager(Authorization::accessManager)
    }.start(7000)
}