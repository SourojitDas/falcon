package ie.tcd.scss.ase.poko

import com.google.gson.annotations.SerializedName

data class BikeResponse(
	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("bike_stands")
	val bikeStands: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("available_bikes")
	val availableBikes: Int? = null,

	@field:SerializedName("bonus")
	val bonus: Boolean? = null,

	@field:SerializedName("last_update")
	val lastUpdate: Long? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("contract_name")
	val contractName: String? = null,

	@field:SerializedName("geo_position")
	val geoPosition: GeoPosition? = null,

	@field:SerializedName("available_bike_stands")
	val availableBikeStands: Int? = null,

	@field:SerializedName("banking")
	val banking: Boolean? = null,

	@field:SerializedName("status")
	val status: String? = null
)