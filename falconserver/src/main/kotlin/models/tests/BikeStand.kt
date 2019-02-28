package models.tests

import  models.bikestand.parseJson
import org.junit.Test

class BikeTest {

    val stubbedBikeRequest = """[{
                "number": 123,
                "contract_name" : "Lyon",
                "name": "stations name",
                "address": "address of the station",
                "position": {
                    "lat": 45.774204,
                    "lng": 4.867512
                    },
                "banking": true,
                "bonus": false,
                "status": "OPEN",
                "bike_stands": 20,
                "available_bike_stands": 15,
                "available_bikes": 5,
                "last_update": "24-05-2019 12:00:00"
                }]"""


    @Test
    fun `test pasrseJson success`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

    }


}
