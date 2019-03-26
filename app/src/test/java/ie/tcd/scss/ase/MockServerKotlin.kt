package ie.tcd.scss.ase


import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest


object MockServerKotlin {

    private val server = MockWebServer()

    fun returnServer(): MockWebServer {
        return server
    }

    init {
        var responseStringBike = "[{" +
                "\"number\": 42," +
                "\"contract_name\": \"Dublin\"," +
                "\"name\": \"SMITHFIELD NORTH\"," +
                "\"address\": \"Smithfield North\"," +
                "\"position\": {" +
                "\"lat\": 53.349562," +
                "\"lng\": -6.278198" +
                "}," +
                "\"banking\": true," +
                "\"bonus\": false," +
                "\"bike_stands\": 30," +
                "\"available_bike_stands\": 26," +
                "\"available_bikes\": 4," +
                "\"status\": \"OPEN\"," +
                "\"last_update\": 1551371764000" +
                "}," +
                "{" +
                "\"number\": 30," +
                "\"contract_name\": \"Dublin\"," +
                "\"name\": \"PARNELL SQUARE NORTH\"," +
                "\"address\": \"Parnell Square North\"," +
                "\"position\": {" +
                "\"lat\": 53.353462," +
                "\"lng\": -6.265305" +
                "}," +
                "\"banking\": true," +
                "\"bonus\": false," +
                "\"bike_stands\": 20," +
                "\"available_bike_stands\": 20," +
                "\"available_bikes\": 0," +
                "\"status\": \"OPEN\"," +
                "\"last_update\": 1551371748000" +
                "}]"

        System.out.println(responseStringBike)


        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                try {

                    if (request.path == "/vls/v1/stations?contract=Dublin&apiKey=ed91f65214a826cb97c5444a15f25665726b95ae") {
                        return MockResponse().setResponseCode(200)
                            .setBody(responseStringBike)
                    } else {
                        MockResponse().setResponseCode(404)
                    }
                } catch (e: IllegalStateException) {
                    System.err.println("no mock set up for " + request.path)
                }

                return MockResponse().setResponseCode(404)
            }
        }

            server.dispatcher = dispatcher
        }
    }

