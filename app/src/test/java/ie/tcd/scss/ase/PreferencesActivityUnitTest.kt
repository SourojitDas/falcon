package ie.tcd.scss.ase

import android.location.Geocoder
import ie.tcd.scss.ase.activites.PreferencesActivity
import org.junit.Test
import android.view.View
import android.widget.EditText
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric
import org.junit.Assert.*
import org.mockito.Mockito.*
import org.mockito.Answers.*

@RunWith(RobolectricTestRunner::class)
class PreferencesActivityUnitTest {

    @Test
    fun `test setPreferences`() {
        val activity = Robolectric.setupActivity(PreferencesActivity::class.java)

        activity.setPreferences("abcd", "efgh")
        val pref = activity.applicationContext.getSharedPreferences("myPref", 0)

        val mockedPreferencesActivity = mock(PreferencesActivity::class.java)
        val work = PreferencesActivity.Lat_Lng(23.01, 23.01)
        val home = PreferencesActivity.Lat_Lng(24.01, 24.01)
        `when`(mockedPreferencesActivity.getLocFromAddress("abcd")).thenReturn(work)
        `when`(mockedPreferencesActivity.getLocFromAddress("efgh")).thenReturn(home)
        `when`(mockedPreferencesActivity.showNotification())

        val worLoc = pref.getString("work_location", "")
        val homeLoc = pref.getString("home_location", "")
        val worLocLong = pref.getString("work_location_long", "")
        val worLocLat = pref.getString("work_location_lat", "")
        val homeLocLong = pref.getString("home_location_long", "")
        val homeLocLat = pref.getString("home_location_lat", "")

        assertEquals(worLoc, "abcd")
        assertEquals(homeLoc, "efgh")
        assertEquals(worLocLong, 23.01)
        assertEquals(worLocLat, 23.01)
        assertEquals(homeLocLong, 24.01)
        assertEquals(homeLocLat, 24.01)
    }
}
