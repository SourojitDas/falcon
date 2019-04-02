package services

import Configuration
import models.events.parseJson

object Event {

    fun getRealTimeEventsInfoByPlace(place: String): models.events.EventsModel? {
        val payload = mapOf(
            "place" to place
        )

        val r = khttp.get(
            Configuration.getEventsServiceBaseURL(), params = payload,
            headers = mapOf("Authorization" to "Bearer " + Configuration.getEventsServiceApiKey())
        )
        return parseJson(r.text)
    }
}
