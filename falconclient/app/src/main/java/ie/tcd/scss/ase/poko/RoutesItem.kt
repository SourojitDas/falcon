package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class RoutesItem(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("overviewPolyline")
	val overviewPolyline: OverviewPolyline? = null,

	@field:SerializedName("copyrights")
	val copyrights: String? = null,

	@field:SerializedName("legs")
	val legs: List<LegsItem?>? = null,

	@field:SerializedName("warnings")
	val warnings: List<Any?>? = null,

	@field:SerializedName("bounds")
	val bounds: Bounds? = null,

	@field:SerializedName("waypointOrder")
	val waypointOrder: List<Any?>? = null
)