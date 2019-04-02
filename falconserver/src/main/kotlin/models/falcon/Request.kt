package models.falcon

data class Preferences(
    val mode: String,
    val selected: Boolean
)

data class Coordinates(
    override val latitude: Double,
    override val longitude: Double
) : models.falcon.CoordinatesModel

data class RequestBody(
    val preferences: List<Preferences>?,
    val origin: Coordinates?,
    val destination: Coordinates?,
    val cityName: String = "Dublin",
    val cityID: String = "2964574"
)
