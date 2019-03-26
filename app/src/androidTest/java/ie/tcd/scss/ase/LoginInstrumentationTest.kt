package ie.tcd.scss.ase

import android.support.annotation.UiThread
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.SdkSuppress
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import ie.tcd.scss.ase.activites.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentationTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(LoginActivity::class.java)


    @Test
    fun signInButtonCheck(){
        onView(withId(R.id.googleSignInButton)).check(matches(isDisplayed()))
    }

}