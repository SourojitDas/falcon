package models.tests

import models.bikestand.parseJson

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

        val fakeNumber = 123
        assert(result!![0].standNumber == (fakeNumber))
        val fakeContract = "Lyon"
        assert(result!![0].contractName.equals(fakeContract))
        val fakeName = "stations name"
        assert(result!![0].standName.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result!![0].standAddress.equals(fakeAddress))
        val fakePosition = models.bikestand.GeoPosition(latitude = 45.77420, longitude = 4.867512)
        assert(result!![0].standGeoLocation?.latitude == fakePosition.latitude)
        assert(result!![0].standGeoLocation?.longitude == fakePosition.longitude)
        val fakeStatus = "OPEN"
        assert(result!![0].standStatus.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result!![0].bikeStands == fakeBikeStands)
        val fakeAvailableStands = 15
        assert(result!![0].bikesAvailable == fakeAvailableStands)


    }


}
