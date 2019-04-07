package ie.tcd.scss.ase.dataclasses

data class ResultMembersItem(
	val country: String? = null,
	val placeHierarchies: List<List<String?>?>? = null,
	val firstSeen: String? = null,
	val timezone: String? = null,
	val start: String? = null,
	val description: String? = null,
	val title: String? = null,
	val relevance: Double? = null,
	val labels: List<String?>? = null,
	val duration: Int? = null,
	val scope: String? = null,
	val rank: Int? = null,
	val end: String? = null,
	val location: List<Double?>? = null,
	val id: String? = null,
	val state: String? = null,
	val category: String? = null,
	val updated: String? = null,
	val localRank: Int? = null
)
