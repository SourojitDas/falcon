package ie.tcd.scss.ase.dataclasses

data class Weather(
	val date: Int? = null,
	val rain: Any? = null,
	val visibility: Int? = null,
	val mainTemperature: MainTemperature? = null,
	val coordinates: Coordinates? = null,
	val clouds: Clouds? = null,
	val systemInfo: SystemInfo? = null,
	val snow: Any? = null,
	val weather: Any? = null,
	val name: String? = null,
	val cod: Int? = null,
	val id: Int? = null,
	val base: String? = null,
	val wind: Wind? = null
)
