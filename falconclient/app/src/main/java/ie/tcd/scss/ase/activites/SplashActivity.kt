package ie.tcd.scss.ase.activites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ie.tcd.scss.ase.MapsActivity
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1000)

                    val sharedPreferenceHelper = SharedPreferenceHelper(applicationContext)
                    val is_logged_in = sharedPreferenceHelper.getPreference(getString(R.string.is_logged_in)).toBoolean()
//                    val firebase_id = sharedPreferenceHelper.getPreference(getString(R.string.firebase_id))
                    if (is_logged_in) {
                        if(sharedPreferenceHelper.containPreference(getString(R.string.pref_saved))){
                            val intent = Intent(applicationContext, MapsActivity::class.java)
                            startActivity(intent)
                        }else {
                            val intent = Intent(applicationContext, PreferencesActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        background.start()
    }
}
