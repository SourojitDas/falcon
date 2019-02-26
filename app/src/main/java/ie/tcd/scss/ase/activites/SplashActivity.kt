package ie.tcd.scss.ase.activites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ie.tcd.scss.ase.MapsActivity
import ie.tcd.scss.ase.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1000)

                    val pref = getApplicationContext().getSharedPreferences("myPref", 0)
                    val is_logged_in = pref.getBoolean(getString(R.string.is_logged_in), false)
                    val firebase_id = pref.getString(getString(R.string.firebase_id), null)
                    if (is_logged_in.equals(true) && firebase_id != null) {
                        if(pref.contains(getString(R.string.pref_saved))){
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
