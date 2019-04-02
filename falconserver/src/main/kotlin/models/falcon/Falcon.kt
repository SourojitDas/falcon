package models.falcon

import models.bikestand.BikeStandModel
import models.googleMaps.*
import models.weather.WeatherModel

data class FalconDirectionsModel(
    var geocodeMembers: List<GeocodedWayoints>? = null,
    var routes: MutableList<FalconRouteModel>? = null,
    var status: String? = null,
    var weather: WeatherModel? = null,
    var bikeStands: List<BikeStandModel>? = null
)

data class FalconRouteModel(
    var mode: String? = null,
    override var bounds: Bounds? = null,
    override var copyrights: String? = null,
    override var legs: List<Leg>? = null,
    override var overviewPolyline: Polyline? = null,
    override var summary: String? = null,
    override var warnings: List<String>? = null,
    override var waypointOrder: List<Int>? = null
) : RouteModel
