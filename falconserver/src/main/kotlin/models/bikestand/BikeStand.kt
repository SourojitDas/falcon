package models.bikestand

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import models.falcon.CoordinatesModel

data class GeoPosition(
    @Json(name = "lng")
    override val longitude: Double,
    @Json(name = "lat")
    override val latitude: Double
) : CoordinatesModel

data class BikeStandModel(
    @Json(name = "number")
    val standNumber: Int? = null,
    @Json(name = "address")
    val address: String? = null,
    @Json(name = "contract_name")
    val contract_name: String? = null,
    @Json(name = "name")
    val standName: String? = null,
    @Json(name = "banking")
    val standBanking: Boolean? = null,
    @Json(name = "position")
    val standGeoLocation: GeoPosition? = null,
    @Json(name = "status")
    val standStatus: String? = null,
    @Json(name = "available_bike_stands")
    val standsAvailable: Int? = null,
    @Json(name = "last_update")
    val standLastUpdate: Long? = null,
    @Json(name = "bonus")
    val bonus: Boolean? = null,
    @Json(name = "bike_stands")
    val bikeStands: Int? = null,
    @Json(name = "available_bikes")
    val bikesAvailable: Int? = null

)


fun parseJson(raw: String): List<BikeStandModel>? {
    return Klaxon().parseArray<BikeStandModel>(raw)
}