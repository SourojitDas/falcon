package ie.tcd.scss.ase

import android.content.Context
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
import org.robolectric.RuntimeEnvironment

@RunWith(MockitoJUnitRunner::class)
class PreferencesUnitTest {

    private val context: Context = RuntimeEnvironment.systemContext

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

        given(sharedPreference.getString(eq(mockPref1.key), anyString())).willReturn(mockPref1.value.toString())
        given(sharedPreference.getString(eq(mockPref2.key), anyString())).willReturn(mockPref2.value.toString())

        given(mockEditor.commit()).willReturn(true)

        // Return the MockEditor when requesting it.
        given(sharedPreference.edit()).willReturn(mockEditor)
        return SharedPreferenceHelper(context)

    }

}
