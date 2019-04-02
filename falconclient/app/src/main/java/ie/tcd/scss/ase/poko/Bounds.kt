package ie.tcd.scss.ase.poko

import com.google.gson.annotations.SerializedName


data class Bounds(

	@field:SerializedName("northEast")
	val northEast: NorthEast? = null,

	@field:SerializedName("southEast")
	val southEast: Any? = null
)