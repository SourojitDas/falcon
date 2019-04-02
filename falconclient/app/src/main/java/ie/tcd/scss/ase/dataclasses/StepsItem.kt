package ie.tcd.scss.ase.dataclasses

data class StepsItem(
	val duration: Duration? = null,
	val htmlInstructions: String? = null,
	val distance: Distance? = null,
	val startLocation: StartLocation? = null,
	val endLocation: EndLocation? = null,
	val polyline: Polyline? = null,
	val maneuver: Any? = null,
	val travelMode: String? = null
)
