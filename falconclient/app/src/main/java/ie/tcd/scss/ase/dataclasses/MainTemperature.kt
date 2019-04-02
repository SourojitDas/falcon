package ie.tcd.scss.ase.dataclasses

data class MainTemperature(
	val tempMax: Double? = null,
	val groundLevel: Any? = null,
	val seaLevel: Any? = null,
	val temperature: Double? = null,
	val humidity: Int? = null,
	val pressure: Int? = null,
	val tempMin: Double? = null
)
