package models.googleMaps

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon


data class GoogleRouteModel(

    @Json(name = "geocoded_waypoints")
    val geocodeMembers: List<GeocodedPoints>? = null,
    @Json(name = "routes")
    val routes: List<Route>? = null,
    @Json(name = "status")
    val status: String? = null
)

data class GeocodedPoints(

    @Json(name = "geocoder_status")
    val geocoderStatus: String? = null,
    @Json(name = "place_id")
    val placeId: String? = null,
    @Json(name = "types")
    val types: List<String>? = null

    )


data class Route(

    @Json(name = "bounds")
    val bounds: Bounds? =null,
    @Json(name = "copyrights")
    val copyrights: String? = null,
    @Json(name = "legs")
    val legs: List<Leg>? = null,
    @Json(name = "overview_polyline")
    val overviewPolyline: Polyline? = null,
    @Json(name = "summary")
    val summary: String? = null,
    @Json(name = "warnings")
    val warnings: List<String>? = null,
    @Json(name = "waypoint_order")
    val waypointOrder: List<Int>? =null



)

data class Coordinates(

    @Json(name = "lat")
    val latitude: Double? = null,
    @Json(name = "lng")
    val longitude: Double? = null

)

data class Bounds(

    @Json(name = "northeast")
    val northEast: Coordinates? = null,
    @Json(name = "southeast")
    val southEast: Coordinates? = null
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


fun parseJson(raw: String): GoogleRouteModel? {
    return Klaxon().parse<GoogleRouteModel>(raw)
}



