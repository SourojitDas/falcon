package ie.tcd.scss.ase

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import ie.tcd.scss.ase.activites.PreferencesActivity
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PreferenceActivityTest{

    @get:Rule
    val activityTestRule = ActivityTestRule(PreferencesActivity::class.java)
//    val activityTestRule = activityScenarioRule<PreferencesActivity>


    @Test
    fun checkViews(){

        onView(withId (R.id.workLocationTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.homeLocationTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.preference_recycle_view)).check(matches(isDisplayed()))
    }

    @Test
    fun captureDataCheck(){

        onView(withId(R.id.workLocationEditView)).perform(ViewActions.clearText(),ViewActions.typeText("CHECK")).check(
            matches(withText("CHECK")))
        onView(withId(R.id.homeLocationEditView)).perform(ViewActions.clearText(),ViewActions.typeText("CHECK")).check(
            matches(withText("CHECK")))

    }




}