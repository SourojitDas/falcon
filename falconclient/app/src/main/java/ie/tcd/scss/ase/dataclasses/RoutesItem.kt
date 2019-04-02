package ie.tcd.scss.ase.dataclasses

data class RoutesItem(
	val mode: String? = null,
	val summary: String? = null,
	val overviewPolyline: OverviewPolyline? = null,
	val copyrights: String? = null,
	val legs: List<LegsItem?>? = null,
	val warnings: List<Any?>? = null,
	val bounds: Bounds? = null,
	val waypointOrder: List<Any?>? = null
)
