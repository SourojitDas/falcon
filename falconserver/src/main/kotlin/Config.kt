private val WeatherServiceBaseURL = "https://api.openweathermap.org/data/2.5/weather"
private val WeatherServiceApiKey = "80d2284f791aa3fbc4c07f2ebcc22c85"
private val BikeServiceApiKey = "ed91f65214a826cb97c5444a15f25665726b95ae"
private val BikeServiceBaseUrl = "https://api.jcdecaux.com/vls/v1/stations"

fun getWeatherServiceBaseURL(): String {
    return WeatherServiceBaseURL
}

fun getWeatherServiceApiKey(): String {
    return WeatherServiceApiKey
}

fun getBikeServiceBaseUrl(): String {
    return BikeServiceBaseUrl
}

fun getBikeServiceApiKey(): String {
    return BikeServiceApiKey
}


