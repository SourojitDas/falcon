package ie.tcd.scss.ase

import ie.tcd.scss.ase.activites.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class LoginUnitTest {

    @Test
    fun loginCheck(){

        val activity =Robolectric.setupActivity(LoginActivity::class.java)
        val pref=activity.applicationContext.getSharedPreferences("myPref",0)



    }

}