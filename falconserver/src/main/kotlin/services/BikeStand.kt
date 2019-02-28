package services

import getBikeServiceApiKey
import getBikeServiceBaseUrl
import models.bikestand.parseJson


class BikeStand {

    fun getRealTimeStandsInfoByCity(contract: String): List<models.bikestand.BikeStandModel>? {
        val payload = mapOf(
            "contract" to contract.toString(),
            "apiKey" to getBikeServiceApiKey()
        )

        val r = khttp.get(getBikeServiceBaseUrl(), params = payload)
        return parseJson(r.text)
    }

}
