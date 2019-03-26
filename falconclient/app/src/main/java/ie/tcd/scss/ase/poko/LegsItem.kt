package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class LegsItem(

	@field:SerializedName("duration")
	val duration: Duration? = null,

	@field:SerializedName("distance")
	val distance: Distance? = null,

	@field:SerializedName("startLocation")
	val startLocation: StartLocation? = null,

	@field:SerializedName("startAddress")
	val startAddress: String? = null,

	@field:SerializedName("trafficSpeedEntry")
	val trafficSpeedEntry: List<Any?>? = null,

	@field:SerializedName("viaWaypoint")
	val viaWaypoint: List<Any?>? = null,

	@field:SerializedName("steps")
	val steps: List<StepsItem?>? = null,

	@field:SerializedName("endLocation")
	val endLocation: EndLocation? = null,

	@field:SerializedName("endAddress")
	val endAddress: String? = null
)