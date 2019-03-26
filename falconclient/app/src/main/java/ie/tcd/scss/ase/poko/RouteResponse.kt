package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class RouteResponse(

	@field:SerializedName("routes")
	val routes: List<RoutesItem?>? = null,

	@field:SerializedName("geocodeMembers")
	val geocodeMembers: List<GeocodeMembersItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)