package ie.tcd.scss.ase.dataclasses

data class LegsItem(
	val duration: Duration? = null,
	val distance: Distance? = null,
	val startLocation: StartLocation? = null,
	val startAddress: String? = null,
	val trafficSpeedEntry: List<Any?>? = null,
	val viaWaypoint: List<Any?>? = null,
	val steps: List<StepsItem?>? = null,
	val endLocation: EndLocation? = null,
	val endAddress: String? = null
)
