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

        val fakeNumber = 123
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations name"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "OPEN"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 15
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }
    @Test
    fun `test pasrseJson fail1`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 123
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations name"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "addres of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "OPEN"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 15
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }


    @Test
    fun `test pasrseJson fail2`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 123
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations name"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "CLOSE"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 15
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }



    @Test
    fun `test pasrseJson fail3`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 123
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "CLOSE"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 15
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }


    @Test
    fun `test pasrseJson fail4`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 123
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "CLOSE"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 150
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }


    @Test
    fun `test pasrseJson fail5`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 567
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "CLOSE"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 20
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 150
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }

    @Test
    fun `test pasrseJson fail6`() {
        val result = parseJson(stubbedBikeRequest)
        assert(result!![0]?.contractName == "Lyon")
        assert(result!![0]?.standAddress == "address of the station")

        val fakeNumber = 567
        assert(result?.number?.equals(fakeNumber)!!)
        val fakeContract = "Lyon"
        assert(result?.contract_name?.equals(fakeContract))
        val fakeName = "stations"
        assert(result?.name?.equals(fakeName))
        val fakeAddress = "address of the station"
        assert(result?.address?.equals(fakeAddress))
        val fakePosition = models.bike.GeoPosition()
        assert(result?.position.lat?.equals(fakePosition.lat))
        assert(result?.position.lng?.equals(fakePosition.lng))
        val fakeStatus = "CLOSE"
        assert(result?.status?.equals(fakeStatus))
        val fakeBikeStands = 200
        assert(result?.bike_stands?.equals(fakeBikeStands))
        val fakeAvailableStands = 150
        assert(result?.available_bike_stands?.equals(fakeAvailableStands))


    }


}
