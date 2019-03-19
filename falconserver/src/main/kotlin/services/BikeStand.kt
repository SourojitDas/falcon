package services

import models.bikestand.parseJson


class BikeStand {

    fun getRealTimeStandsInfoByCity(contract: String): List<models.bikestand.BikeStandModel>? {
        val payload = mapOf(
            "contract" to contract,
            "apiKey" to Configuration.getBikeServiceApiKey()
        )

        val r = khttp.get(Configuration.getBikeServiceBaseURL(), params = payload)
        return parseJson(r.text)
    }
}
