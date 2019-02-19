package ie.tcd.scss.ase

import android.content.SharedPreferences
import org.junit.Test
import ie.tcd.scss.ase.poko.SharedPreferenceDataClass
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PreferencesUnitTest {

//    private val context: Context = RuntimeEnvironment.application.getApplicationContext()

    var mockPref1 = SharedPreferenceDataClass("worklocation","work")
    var mockPref2 = SharedPreferenceDataClass("homelocation","home")

    lateinit var prefernceData:ArrayList<SharedPreferenceDataClass>
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    @Mock lateinit var sharedPreference : SharedPreferences
    @Mock lateinit var mockEditor: SharedPreferences.Editor


    @Before
    fun initMock(){
        prefernceData = ArrayList()
        prefernceData.add(mockPref1)
        prefernceData.add(mockPref2)

//        sharedPreference =  PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferenceHelper = createSharedPreferenceHelper()

    }

    @Test
    fun testSavePreferenceHelper(){
        assertTrue(sharedPreferenceHelper.savePreference(prefernceData))

        val workData = sharedPreferenceHelper.getPreference(mockPref1.key)
        val homeData = sharedPreferenceHelper.getPreference(mockPref2.key)

        assertEquals(mockPref1.value,workData)
        assertEquals(mockPref2.value,homeData)

    }

    private fun createSharedPreferenceHelper(): SharedPreferenceHelper {

        given(sharedPreference.getString(eq(mockPref1.key), anyString())).willReturn(mockPref1.value)
        given(sharedPreference.getString(eq(mockPref2.key), anyString())).willReturn(mockPref2.value)

        given(mockEditor.commit()).willReturn(true)

        // Return the MockEditor when requesting it.
        given(sharedPreference.edit()).willReturn(mockEditor)
        return SharedPreferenceHelper(sharedPreference)

    }



//
//    @Test
//    fun testSetPreferences() {
//
//
//
//
//
//        val activity = Robolectric.setupActivity(PreferencesActivity::class.java)
//
//        activity.setPreferences("abcd", "efgh")
//        val pref = activity.applicationContext.getSharedPreferences("myPref", 0)
//
//        val mockedPreferencesActivity = mock(PreferencesActivity::class.java)
//        val work = PreferencesActivity.Lat_Lng(23.01, 23.01)
//        val home = PreferencesActivity.Lat_Lng(24.01, 24.01)
//        `when`(mockedPreferencesActivity.getLocFromAddress("abcd")).thenReturn(work)
//        `when`(mockedPreferencesActivity.getLocFromAddress("efgh")).thenReturn(home)
//        `when`(mockedPreferencesActivity.showNotification())
//
//        val worLoc = pref.getString("work_location", "")
//        val homeLoc = pref.getString("home_location", "")
//        val worLocLong = pref.getString("work_location_long", "")
//        val worLocLat = pref.getString("work_location_lat", "")
//        val homeLocLong = pref.getString("home_location_long", "")
//        val homeLocLat = pref.getString("home_location_lat", "")
//
//        assertEquals(worLoc, "abcd")
//        assertEquals(homeLoc, "efgh")
//        assertEquals(worLocLong, 23.01)
//        assertEquals(worLocLat, 23.01)
//        assertEquals(homeLocLong, 24.01)
//        assertEquals(homeLocLat, 24.01)
//    }


}
