package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class StartLocation(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)