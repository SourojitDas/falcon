package ie.tcd.scss.ase;

import ie.tcd.scss.ase.interfaces.RetroFitAPIClient
import ie.tcd.scss.ase.rest.RetrofitBuilder
import junit.framework.TestCase
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AuthCallTest {

    @Test
    fun retrofitTest() {
        var mockWebServer: MockWebServer = MockServerKotlin.returnServer()

        System.out.println("MOCKSERVER :" + mockWebServer.url("").toString())

        val retrofit = RetrofitBuilder.retrofitBuilder(mockWebServer.url("").toString())

        val service = retrofit.create(RetroFitAPIClient::class.java)
        val call = service.getBikeData("Dublin", "ed91f65214a826cb97c5444a15f25665726b95ae")
        TestCase.assertTrue(call.execute().isSuccessful)

        mockWebServer.shutdown()

    }

}