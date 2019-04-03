package models.events

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

data class EventsModel(
    @Json(name = "count")
    val count: Int? = null,
    @Json(name = "overflow")
    val overflow: Boolean? = null,
    @Json(name = "next")
    val next: String? = null,
    @Json(name = "previous")
    val previous: String? = null,
    @Json(name = "results")
    val resultMembers: List<Result>? = null
)

data class Result(
    @Json(name = "relevance")
    val relevance: Double? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "category")
    val category: String? = null,
    //Clarify
    @Json(name = "labels")
    val labels: List<String>? = null,
    @Json(name = "rank")
    val rank: Int? = null,
    @Json(name = "local_rank")
    val localRank: Int? = null,
    @Json(name = "duration")
    val duration: Int? = null,
    @Json(name = "start")
    val start: String? = null,
    @Json(name = "end")
    val end: String? = null,
    @Json(name = "updated")
    val updated: String? = null,
    @Json(name = "first_seen")
    val firstSeen: String? = null,
    @Json(name = "timezone")
    val timezone: String? = null,
    @Json(name = "location")
    val location: List<Double>? = null,
    @Json(name = "scope")
    val scope: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "place_hierarchies")
    val placeHierarchies: List<List<String>>? = null,
    @Json(name = "state")
    val state: String? = null
)

//Clarify on this line
fun parseJson(raw: String): EventsModel? {
    return Klaxon().parse<EventsModel>(raw)
}