package models.googleMaps

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

data class GoogleDirectionsModel(
    @Json(name = "geocoded_waypoints")
    var geocodeMembers: List<GeocodedWayoints>? = null,
    @Json(name = "routes")
    var routes: MutableList<GoogleRouteModel>? = null,
    @Json(name = "status")
    var status: String? = null
)

data class GeocodedWayoints(

    @Json(name = "geocoder_status")
    val geocoderStatus: String? = null,
    @Json(name = "place_id")
    val placeId: String? = null,
    @Json(name = "types")
    val types: List<String>? = null

    )


interface RouteModel {

    val bounds: Bounds?
    val copyrights: String?
    val legs: List<Leg>?
    val overviewPolyline: Polyline?
    val summary: String?
    val warnings: List<String>?
    val waypointOrder: List<Int>?
}

data class GoogleRouteModel(
    @Json(name = "bounds")
    override val bounds: Bounds? = null,
    @Json(name = "copyrights")
    override val copyrights: String? = null,
    @Json(name = "legs")
    override val legs: List<Leg>? = null,
    @Json(name = "overview_polyline")
    override val overviewPolyline: Polyline? = null,
    @Json(name = "summary")
    override val summary: String? = null,
    @Json(name = "warnings")
    override val warnings: List<String>? = null,
    @Json(name = "waypoint_order")
    override val waypointOrder: List<Int>? = null
) : RouteModel

data class Coordinates(

    @Json(name = "lat")
    override val latitude: Double,
    @Json(name = "lng")
    override val longitude: Double

) : models.falcon.CoordinatesModel

data class Bounds(

    @Json(name = "northeast")
    val northEast: Coordinates? = null,
    @Json(name = "southwest")
    val southWest: Coordinates? = null
)





data class Leg(

    @Json(name = "distance")
    val distance: Distance? = null,
    @Json(name = "duration")
    val duration: Duration? = null,
    @Json(name = "end_location")
    val endLocation: Coordinates? = null,
    @Json(name = "start_location")
    val startLocation: Coordinates? = null,
    @Json(name = "steps")
    val steps: List<Step>? = null,
    @Json(name = "end_address")
    val endAddress: String? = null,
    @Json(name = "start_address")
    val startAddress: String? = null,
    @Json(name = "traffic_speed_entry")
    val trafficSpeedEntry: List<String>? = null,
    @Json(name = "via_waypoint")
    val viaWaypoint: List<String>? = null

)

data class Distance(

    @Json(name = "text")
    val text: String? = null,
    @Json(name = "value")
    val value: Int? = null
)


data class Duration(

    @Json(name = "text")
    val text: String? = null,
    @Json(name = "value")
    val value: Int? = null
)




data class Polyline(

    @Json(name = "points")
    val points: String? = null
)

data class Step(

    @Json(name = "distance")
    val distance: Distance? = null,
    @Json(name = "duration")
    val duration: Duration? = null,
    @Json(name = "end_location")
    val endLocation: Coordinates? = null,
    @Json(name = "html_instructions")
    val htmlInstructions: String? = null,
    @Json(name = "polyline")
    val polyline: Polyline? = null,
    @Json(name = "start_location")
    val startLocation: Coordinates? = null,
    @Json(name = "travel_mode")
    val travelMode: String? = null,
    @Json(name = "maneuver")
    val maneuver: String? = null
)


fun parseJson(raw: String): GoogleDirectionsModel? {
    return Klaxon().parse<GoogleDirectionsModel>(raw)
}



