package ie.tcd.scss.ase


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import ie.tcd.scss.ase.activites.MapsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapInstrumentationTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MapsActivity::class.java)


    @Test
    fun checkMap(){

        onView(withId(R.id.source_location_autocomplete_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.destination_location_autocomplete_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.directionSearchButton)).check(matches(isDisplayed()))
        onView(withId(R.id.map)).check(matches(isDisplayed()))
        //text fields missing
    }

    @Test
    fun captureDataCheck(){



    }
}
