package ie.tcd.scss.ase.dataclasses

data class RouteBody (
    val preferences: List<Preferences>,
    val origin: Coordinates,
    val destination: Coordinates
)