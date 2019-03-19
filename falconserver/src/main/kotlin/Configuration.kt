import com.typesafe.config.ConfigFactory


object Configuration {
    private val WeatherServiceBaseURLPath = "weather_service.base_url"
    private val WeatherServiceApiKeyPath = "weather_service.api_key"
    private val BikeServiceApiKeyPath = "bike_service.api_key"
    private val BikeServiceBaseURLPath = "bike_service.base_url"
    private val GoogleMapsServiceBaseURLPath = "map_service.base_url"
    private val GoogleMapsServiceApiKeyPath = "bike_service.api_key"

    private var configFile = ConfigFactory.load("config/application")

    fun getWeatherServiceBaseURL(): String {
        return configFile.getString(WeatherServiceBaseURLPath)
    }

    fun getWeatherServiceApiKey(): String {
        return configFile.getString(WeatherServiceApiKeyPath)
    }

    fun getBikeServiceBaseURL(): String {
        return configFile.getString(BikeServiceBaseURLPath)
    }

    fun getBikeServiceApiKey(): String {
        return configFile.getString(BikeServiceApiKeyPath)
    }

    fun getGoogleMapsServiceBaseURL(): String {
        return configFile.getString(GoogleMapsServiceBaseURLPath)
    }

    fun getGoogleMapsServiceApiKey(): String {
        return configFile.getString(GoogleMapsServiceApiKeyPath)
    }


}