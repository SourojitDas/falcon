import io.javalin.Javalin
import handlers.*
import io.javalin.security.SecurityUtil.roles
import com.google.firebase.FirebaseApp
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseOptions
import java.io.File
import java.io.FileInputStream


fun main(/*args: Array<String>*/) {

    bootstrapFirebaseSDK()
    val server = createServer()
    bootstrapRoutes(server)

}

fun bootstrapRoutes(server: Javalin) {
    server.get("/weather", WeatherController::getWeatherByCoordinates, roles(ApiRole.USER_READ))
    server.get("/bikestand", BikeStandController::getRealTimeStandsInfoByCity, roles(ApiRole.USER_READ))
    server.get("/route", GoogleRouteController::getRouteByOriginAndDestination, roles(ApiRole.USER_READ))
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