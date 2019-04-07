package services

import Configuration
import ch.hsr.geohash.GeoHash
import handlers.HeuristicCalculation
import models.bikestand.BikeStandModel
import models.falcon.Coordinates
import models.falcon.FalconDirectionsModel
import models.falcon.FalconRouteModel
import models.googleMaps.*
import kotlin.random.Random

object GoogleRoute : GoogleRouteInterface {
    val DrivingMode = "driving"
    val WalkingMode = "walking"
    val BicyclingMode = "bicycling"
    val TransitMode = "transit"

    lateinit var bikeStandOrgin: BikeStandModel
    lateinit var bikeStandDestination: BikeStandModel

    override fun getRouteByEndpointsAndMode(
        origin: String,
        destination: String,
        mode: String
    ): models.googleMaps.GoogleDirectionsModel? {
        val payload = mapOf(
            "origin" to origin,
            "destination" to destination,
            "mode" to mode,
            "key" to Configuration.getGoogleMapsServiceApiKey()
        )
        val r = khttp.get(Configuration.getGoogleMapsServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }

    fun getRouteByEndpointsAndModeAndAlternate(
        origin: String,
        destination: String,
        mode: String
    ): models.googleMaps.GoogleDirectionsModel? {
        val payload = mapOf(
            "origin" to origin,
            "destination" to destination,
            "mode" to mode,
            "alternatives" to "true",
            "key" to Configuration.getGoogleMapsServiceApiKey()
        )
//        println(payload)
        val r = khttp.get(Configuration.getGoogleMapsServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }

    override fun getMultiModeRoute(origin: String, destination: String): List<FalconDirectionsModel?> {
        val res = mutableListOf<FalconDirectionsModel>()
        for (mode in listOf(DrivingMode, WalkingMode, BicyclingMode, TransitMode)) {
            val directionsFromGoogle = getRouteByEndpointsAndMode(origin, destination, mode)
            var modifiedDirectionsObject = FalconDirectionsModel()
            modifiedDirectionsObject = _updateDirectionsObject(modifiedDirectionsObject, directionsFromGoogle!!)
            modifiedDirectionsObject.routes = mutableListOf()

            for (route in directionsFromGoogle.routes!!) {
                var newRoute = FalconRouteModel()
                newRoute = _updateRouteObject(newRoute, route)
                newRoute.mode = mode
                modifiedDirectionsObject.routes?.add(newRoute)
            }
            res.add(modifiedDirectionsObject)
        }
        return res
    }

    override fun _updateDirectionsObject(
        falconObj: FalconDirectionsModel,
        googleObj: GoogleDirectionsModel
    ): FalconDirectionsModel =
        falconObj.apply {
            geocodeMembers = googleObj.geocodeMembers
            status = googleObj.status
        }

    override fun _updateRouteObject(falconObj: FalconRouteModel, googleObj: GoogleRouteModel): FalconRouteModel =
        falconObj.apply {
            bounds = googleObj.bounds
            copyrights = googleObj.copyrights
            legs = googleObj.legs
            overviewPolyline = googleObj.overviewPolyline
            summary = googleObj.summary
            warnings = googleObj.warnings
            waypointOrder = googleObj.waypointOrder
        }

    fun getCustomRoute(
        origin: Coordinates,
        destination: Coordinates,
        bikeStands: List<BikeStandModel>?
    ): List<FalconDirectionsModel?> {


        var res = getMultiModeRoute(
            "${origin.latitude},${origin.longitude}",
            "${destination.latitude},${destination.longitude}"
        ).toMutableList() as MutableList<FalconDirectionsModel>

        var foundGeoHashOrigin: Boolean = false
        var foundGeoHash = false


        for (j in 8 downTo 5) {
            for (i in bikeStands!!.indices) {

                val geoHash = GeoHash.withCharacterPrecision(
                    bikeStands[i].standGeoLocation!!.latitude,
                    bikeStands[i].standGeoLocation!!.longitude,
                    j
                )
                val geoHashOrgin = GeoHash.withCharacterPrecision(origin.latitude, origin.longitude, j)

                if (geoHash == geoHashOrgin) {
                    if (foundGeoHashOrigin) {

                        val firstBikeStandCoordiante = Coordinates(
                            bikeStandOrgin.standGeoLocation!!.latitude,
                            bikeStandOrgin.standGeoLocation!!.longitude
                        )
                        val secondBikeStandCoordiante = Coordinates(
                            bikeStands[i].standGeoLocation!!.latitude,
                            bikeStands[i].standGeoLocation!!.longitude
                        )

                        val firstDistance = HeuristicCalculation.getHaversineDistance(origin, firstBikeStandCoordiante)
                        val secondDistance =
                            HeuristicCalculation.getHaversineDistance(origin, secondBikeStandCoordiante)

                        if (secondDistance < firstDistance) {
                            bikeStandOrgin = bikeStands[i]
                        }

                    } else {
                        foundGeoHashOrigin = true
                        bikeStandOrgin = bikeStands[i]
                        continue
                    }
                }
            }

            if (foundGeoHashOrigin) {
                break
            }
        }

        for (j in 8 downTo 5) {
            for (i in bikeStands!!.indices) {

                val geoHash = GeoHash.withCharacterPrecision(
                    bikeStands[i].standGeoLocation!!.latitude,
                    bikeStands[i].standGeoLocation!!.longitude,
                    j
                )
                val geoHashDestination = GeoHash.withCharacterPrecision(destination.latitude, destination.longitude, j)

                if (geoHash == geoHashDestination) {
                    if (foundGeoHash) {

                        val firstBikeStandCoordiante = Coordinates(
                            bikeStandDestination.standGeoLocation!!.latitude,
                            bikeStandDestination.standGeoLocation!!.longitude
                        )
                        val secondBikeStandCoordiante = Coordinates(
                            bikeStands[i].standGeoLocation!!.latitude,
                            bikeStands[i].standGeoLocation!!.longitude
                        )

                        val firstDistance =
                            HeuristicCalculation.getHaversineDistance(destination, firstBikeStandCoordiante)
                        val secondDistance =
                            HeuristicCalculation.getHaversineDistance(destination, secondBikeStandCoordiante)

                        if (secondDistance < firstDistance) {
                            bikeStandDestination = bikeStands[i]
                        }

                    } else {
                        foundGeoHash = true
                        bikeStandDestination = bikeStands[i]
                    }
                }
            }

            if (foundGeoHash) {
                break
            }
        }

//        var resmodel = FalconDirectionsModel()

        if (!foundGeoHash) {


            for (j in 8 downTo 5) {

                for (i in bikeStands!!.indices) {

                    val geoHash = GeoHash.withCharacterPrecision(
                        bikeStands[i].standGeoLocation!!.latitude,
                        bikeStands[i].standGeoLocation!!.longitude,
                        j
                    )
                    val neighbors = geoHash.adjacent

                    for (k in 0..(neighbors.size - 1)) {
                        val geoHashNeighbour = neighbors[k]
                        val geoHashDestination =
                            GeoHash.withCharacterPrecision(destination.latitude, destination.longitude, j)

                        if (geoHashNeighbour == geoHashDestination) {
                            if (foundGeoHash) {

                                val firstBikeStandCoordiante = Coordinates(
                                    bikeStandDestination.standGeoLocation!!.latitude,
                                    bikeStandDestination.standGeoLocation!!.longitude
                                )
                                val secondBikeStandCoordiante = Coordinates(
                                    bikeStands[i].standGeoLocation!!.latitude,
                                    bikeStands[i].standGeoLocation!!.longitude
                                )

                                val firstDistance =
                                    HeuristicCalculation.getHaversineDistance(destination, firstBikeStandCoordiante)
                                val secondDistance =
                                    HeuristicCalculation.getHaversineDistance(destination, secondBikeStandCoordiante)

                                if (secondDistance < firstDistance) {
                                    bikeStandDestination = bikeStands[i]
                                }

                            } else {
                                foundGeoHash = true
                                bikeStandDestination = bikeStands[i]
                            }
                        }
                    }
                }
                if (foundGeoHash) {
                    break
                }
            }

        }

        if (::bikeStandOrgin.isInitialized && ::bikeStandDestination.isInitialized && foundGeoHash && foundGeoHashOrigin) {

            val directionsFromGoogleOrigin = getRouteByEndpointsAndMode(
                "${origin.latitude},${origin.longitude}",
                "${bikeStandOrgin.standGeoLocation?.latitude},${bikeStandOrgin.standGeoLocation?.longitude}", "walking"
            )
            val directionsFromGoogleCycling = getRouteByEndpointsAndMode(
                "${bikeStandOrgin.standGeoLocation!!.latitude},${bikeStandOrgin.standGeoLocation!!.longitude}",
                "${bikeStandDestination.standGeoLocation?.latitude},${bikeStandDestination.standGeoLocation?.longitude}",
                "bicycling"
            )
            val directionsFromGoogleDestination = getRouteByEndpointsAndMode(
                "${bikeStandDestination.standGeoLocation?.latitude},${bikeStandDestination.standGeoLocation?.longitude}",
                "${destination.latitude},${destination.longitude}", "transit"
            )

            var modifiedDirectionsObjectOrigin = FalconDirectionsModel()
            modifiedDirectionsObjectOrigin =
                _updateDirectionsObject(modifiedDirectionsObjectOrigin, directionsFromGoogleOrigin!!)
            modifiedDirectionsObjectOrigin.routes = mutableListOf()

            var modifiedDirectionsObjectCycling = FalconDirectionsModel()
            modifiedDirectionsObjectCycling = _updateDirectionsObject(
                modifiedDirectionsObjectCycling,
                directionsFromGoogleOrigin
            )
            modifiedDirectionsObjectCycling.routes = mutableListOf()

            var modifiedDirectionsObjectDesitination = FalconDirectionsModel()
            modifiedDirectionsObjectDesitination = _updateDirectionsObject(
                modifiedDirectionsObjectDesitination,
                directionsFromGoogleOrigin
            )
            modifiedDirectionsObjectDesitination.routes = mutableListOf()

            for (route in directionsFromGoogleOrigin.routes!!) {
                var newRoute = FalconRouteModel()
                newRoute = _updateRouteObject(newRoute, route)
                newRoute.mode = "multimode"
                modifiedDirectionsObjectOrigin.routes?.add(newRoute)
            }

            val routeSize = modifiedDirectionsObjectOrigin.routes?.size as Int

            for (route in directionsFromGoogleCycling!!.routes!!) {
                val listOfLegs =
                    modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs?.toMutableList() as MutableList<Leg>
                listOfLegs.addAll(route.legs as List<Leg>)
                modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs = listOfLegs
            }

            for (route in directionsFromGoogleDestination!!.routes!!) {
                val listOfLegs =
                    modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs?.toMutableList() as MutableList<Leg>
                listOfLegs.addAll(route.legs as List<Leg>)
                modifiedDirectionsObjectOrigin.routes!![routeSize.minus(1)].legs = listOfLegs
//                modifiedDirectionsObjectOrigin.routes!![modifiedDirectionsObjectOrigin.routes!!.size - 1].legs?.toMutableList()!!.addAll(newRoute.legs!!)
            }

//            resmodel = modifiedDirectionsObjectOrigin

            res.add(modifiedDirectionsObjectOrigin)

        }

        var driveMode = getDriveMode(res).copy()
        res.remove(driveMode)
        val driveRoute = getDriveRoute(driveMode).copy()
        if (driveRoute != null) {
            driveMode.routes?.remove(driveRoute.copy())
            driveMode.routes?.add(mockBlocks(driveRoute.copy()).copy())
        }
        res.add(driveMode)

        return res
    }

    private fun getDriveRoute(driveMode: FalconDirectionsModel): FalconRouteModel {
        var removeRoute = FalconRouteModel()
        driveMode.routes?.forEach { route ->
            var routeModel = route
            if (routeModel.mode!! == "driving") {
                removeRoute = routeModel
            }
        }

        return removeRoute
    }

    private fun getDriveMode(directions: MutableList<FalconDirectionsModel>): FalconDirectionsModel {
        var driveMode = FalconDirectionsModel()

        directions.forEach { direction ->

            direction.routes?.forEach { route ->
                var routeModel = route
                if (routeModel.mode!! == "driving") {

                    driveMode = direction

                }
            }
        }

        return driveMode

    }

    private fun mockBlocks(route: FalconRouteModel): FalconRouteModel {

        var routeModel = route.copy()

        var legs = (routeModel.legs as List<Leg>).toMutableList()
        var steps = (legs[0].steps as List<Step>).toMutableList()
        var stepsCopy = mutableListOf<Step>()
        stepsCopy.addAll(steps)
        if (steps.size > 2) {
            var pos = Random.nextInt(0, steps.size)
            var newSteps = mutableListOf<Step>()
            steps[pos].blocked = true
            newSteps.addAll(steps)
            newSteps.addAll(getRouteForStep(steps[pos].copy(), pos, steps))
            routeModel.legs!!.toMutableList()[0].steps = null
            routeModel.legs!!.toMutableList()[0].steps = newSteps
        }

        return routeModel
    }

    private fun getRouteForStep(
        step: Step,
        pos: Int,
        steps: MutableList<Step>
    ): MutableList<Step> {

        var position = pos

        var newStep = mutableListOf<Step>()

        var origin = "${step.startLocation?.latitude},${step.startLocation?.longitude}"
        var destination = "${step.endLocation?.latitude},${step.endLocation?.longitude}"
        val directionsFromGoogle = getRouteByEndpointsAndModeAndAlternate(origin, destination, "driving")

        val routes = directionsFromGoogle!!.routes?.toMutableList()

        routes?.forEach { route ->
            var legs = route.legs?.toMutableList()
            legs?.forEach { leg ->
                val steps = leg.steps?.toMutableList()
                steps?.forEach { singleStep ->
                    if (singleStep.polyline!!.points!! != step.polyline!!.points!!) {
                        newStep.add(singleStep.copy())
                    }
                }
            }
        }

        while ((steps.size != position + 1) && (newStep.size == 0)) {
            position += 1
            var origin = "${step.startLocation?.latitude},${step.startLocation?.longitude}"
            var destination = "${steps[position].endLocation?.latitude},${steps[position].endLocation?.longitude}"
            val directionsFromGoogle = getRouteByEndpointsAndModeAndAlternate(origin, destination, "driving")

            val routes = directionsFromGoogle!!.routes?.toMutableList()

            routes?.forEach { route ->
                var legs = route.legs?.toMutableList()
                legs?.forEach { leg ->
                    val steps = leg.steps?.toMutableList()
                    steps?.forEach { singleStep ->
                        if (singleStep.polyline!!.points!! != step.polyline!!.points!!) {
                            newStep.add(singleStep.copy())
                        }
                    }
                }
            }
        }

        return newStep
    }

}

interface GoogleRouteInterface {
    fun getRouteByEndpointsAndMode(
        origin: String,
        destination: String,
        mode: String
    ): models.googleMaps.GoogleDirectionsModel?

    fun getMultiModeRoute(origin: String, destination: String): List<FalconDirectionsModel?>
    fun _updateDirectionsObject(
        falconObj: FalconDirectionsModel,
        googleObj: GoogleDirectionsModel
    ): FalconDirectionsModel

    fun _updateRouteObject(falconObj: FalconRouteModel, googleObj: GoogleRouteModel): FalconRouteModel
}