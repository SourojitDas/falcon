package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class GeocodeMembersItem(

	@field:SerializedName("types")
	val types: List<String?>? = null,

	@field:SerializedName("placeId")
	val placeId: String? = null,

	@field:SerializedName("geocoderStatus")
	val geocoderStatus: String? = null
)