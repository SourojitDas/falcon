package ie.tcd.scss.ase.dataclasses

data class Events(
	val next: String? = null,
	val overflow: Boolean? = null,
	val previous: Any? = null,
	val resultMembers: List<ResultMembersItem?>? = null,
	val count: Int? = null
)
