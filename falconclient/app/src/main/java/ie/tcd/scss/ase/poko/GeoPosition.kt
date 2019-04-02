package ie.tcd.scss.ase.poko

import com.google.gson.annotations.SerializedName


data class GeoPosition(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)