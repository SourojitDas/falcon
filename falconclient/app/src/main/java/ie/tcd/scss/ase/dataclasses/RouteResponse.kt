package ie.tcd.scss.ase.dataclasses

data class RouteResponse(
	val routes: List<RoutesItem?>? = null,
	val weather: Weather? = null,
	val bikeStands: List<BikeStandsItem?>? = null,
	val geocodeMembers: List<GeocodeMembersItem?>? = null,
	val events: Any? = null,
	val status: String? = null
)
