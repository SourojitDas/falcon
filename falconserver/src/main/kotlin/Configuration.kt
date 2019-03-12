import com.typesafe.config.ConfigFactory


object Configuration {
    private val WeatherServiceBaseURLKey = "weather_service.base_url"
    private val WeatherServiceApiKey = "weather_service.api_key"
    private val BikeServiceApiKey = "bike_service.api_key"
    private val BikeServiceBaseUrl = "bike_service.base_url"

    private var configFile = ConfigFactory.load("config/application")

    fun getWeatherServiceBaseURL(): String {
        return configFile.getString(WeatherServiceBaseURLKey)
    }

    fun getWeatherServiceApiKey(): String {
        return configFile.getString(WeatherServiceApiKey)
    }

    fun getBikeServiceBaseUrl(): String {
        return configFile.getString(BikeServiceBaseUrl)
    }

    fun getBikeServiceApiKey(): String {
        return configFile.getString(BikeServiceApiKey)
    }

}