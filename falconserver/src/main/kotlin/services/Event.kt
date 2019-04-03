package services

import Configuration
import models.events.parseJson


object Event: EventInterface{
    override fun getRealTimeEventsInfoByPlace(place: String): models.events.EventsModel? {
        val payload = mapOf(
            "country" to place
        )
        val r = khttp.get(
            Configuration.getEventsServiceBaseURL(), params = payload,
            headers = mapOf("Authorization" to "Bearer " + Configuration.getEventsServiceApiKey())
        )
        return parseJson(r.text)
    }
}

interface EventInterface {
    fun getRealTimeEventsInfoByPlace(place: String): models.events.EventsModel?
}