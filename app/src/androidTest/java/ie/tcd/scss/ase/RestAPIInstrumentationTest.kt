package ie.tcd.scss.ase

import android.content.Context
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.content.res.AssetManager
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import ie.tcd.scss.ase.activites.TestActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


@RunWith(AndroidJUnit4::class)
class RestAPIInstrumentationTest{
    @Rule
    val activityTestRule: ActivityTestRule<TestActivity> = ActivityTestRule<TestActivity>(TestActivity::class.java)
    var server: MockWebServer = MockWebServer()

    @Throws(Exception::class)
    fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        while (reader.readLine()!=null) {
            sb.append(reader.readLine()).append("\n")
        }
        reader.close()
        return sb.toString()
    }

    @Before
    fun setUp(){
        server = MockWebServer()
        server.start()
        // val insRegistry: InstrumentationRegistry = InstrumentationRegistry()
        InstrumentationRegistry.getInstrumentation()

    }


    @Test
    fun testRESTClient(){
        val fileName = "bikeResponse.JSON"
        server.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(convertStreamToString(InstrumentationRegistry.getInstrumentation().getContext().getResources().getAssets().open(fileName))))
        val intent =  Intent();
        activityTestRule.launchActivity(intent);

        onView(withText("Smithfield North")).check(ViewAssertions.matches(isDisplayed()));
    }

    @After
    fun tearDown(){
        server.shutdown();
    }


//    public static String getStringFromFile(Context context, String filePath) throws Exception {
//        final InputStream stream = context.getResources().getAssets().open(filePath);
//
//        String ret = convertStreamToString(stream);
//        //Make sure you close all streams.
//        stream.close();
//        return ret;
//    }

}