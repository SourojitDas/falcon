package ie.tcd.scss.ase.dataclasses

data class BikeStandsItem(
	val standNumber: Int? = null,
	val standBanking: Boolean? = null,
	val address: String? = null,
	val bonus: Boolean? = null,
	val bikesAvailable: Int? = null,
	val contractName: String? = null,
	val standGeoLocation: StandGeoLocation? = null,
	val bikeStands: Int? = null,
	val standName: String? = null,
	val standStatus: String? = null,
	val standsAvailable: Int? = null,
	val standLastUpdate: Long? = null
)
