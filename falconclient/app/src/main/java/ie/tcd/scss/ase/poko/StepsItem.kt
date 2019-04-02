package ie.tcd.scss.ase.poko


import com.google.gson.annotations.SerializedName


data class StepsItem(

	@field:SerializedName("duration")
	val duration: Duration? = null,

	@field:SerializedName("htmlInstructions")
	val htmlInstructions: String? = null,

	@field:SerializedName("distance")
	val distance: Distance? = null,

	@field:SerializedName("startLocation")
	val startLocation: StartLocation? = null,

	@field:SerializedName("endLocation")
	val endLocation: EndLocation? = null,

	@field:SerializedName("polyline")
	val polyline: Polyline? = null,

	@field:SerializedName("maneuver")
	val maneuver: Any? = null,

	@field:SerializedName("travelMode")
	val travelMode: String? = null
)