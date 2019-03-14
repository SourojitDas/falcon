package models.googleMaps

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon


data class Response(

    @Json(name = "geocoded_waypoints")
    val geocodeMembers: Array<GeocodedPoints>? = null,
    @Json(name = "routes")
    val routes: routes? = null,
    @Json(name = "status")
    val status: status? = null
)

data class GeocodedPoints(

    @Json(name = "geocoder_status")
    val geocoderStatus: Double? = null,
    @Json(name = "place_id")
    val placeId: Double? = null,
    @Json(name = "types")
    val types: Double? = null

    )


data class Routes(

    @Json(name = "bounds")
    val bounds: Bounds? =null,
    @Json(name = "copyrights")
    val copyrights: String? = null,
    @Json(name = "legs")
    val legs: Leg? = null,
    @Json(name = "overview_polyline")
    val overviewPolyline: Pollyline? = null,
    @Json(name = "summary")
    val summary: String? = null
    //@Json(name = "warnings")
    //val warning: Array<>? = null
    //@Json(name = "waypoint_order")
    //val waypointOrder: Array<>? =null



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
    val endLocation: EndLocation? = null,
    @Json(name = "start_location")
    val startLocation: StartLocation? = null,
    @Json(name = "steps")
    val steps: Array<Step>? = null,
    @Json(name = "end_address")
    val endAddress: String? = null,
    @Json(name = "start_address")
    val startAddress: String? = null

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



data class EndLocation(
    @Json(name = "lat")
    val lat: Double? = null,
    @Json(name = "lng")
    val lng: Double? = null

)


data class StartLocation(
    @Json(name = "lat")
    val lat: Double? = null,
    @Json(name = "lng")
val lng: Double? = null

)

data class Pollyline(

    @Json(name = "points")
    val points: String? = null
)

data class Step(

    @Json(name = "distance")
    val distance: Distance? = null,
    @Json(name = "duration")
    val duration: Duration? = null,
    @Json(name = "end_location")
    val endLocation: EndLocation? = null,
    @Json(name = "html_instructions")
    val htmlInstructionsn: String? = null,
    @Json(name = "distance")
    val pollyline: Pollyline? = null,
    @Json(name = "start_location")
    val startLocation: StartLocation? = null,
    @Json(name = "travel_mode")
    val travelMode: String? = null
)




