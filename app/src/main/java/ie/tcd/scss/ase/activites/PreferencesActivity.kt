package ie.tcd.scss.ase.activites

import android.location.Address
import android.location.Geocoder
import android.net.wifi.hotspot2.pps.HomeSp
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import ie.tcd.scss.ase.R
import java.lang.Exception

open class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)
        val sharedPref = getSharedPreferences("myPref", 0)
        if (sharedPref.contains("wLoc")) {
            wl.setText(sharedPref.getString("wLoc", ""))
        }
        if (sharedPref.contains("hLoc")) {
            hl.setText(sharedPref.getString("hLoc", ""))
        }
    }

    data class Lat_Lng(val lat: Double, val lng: Double)

    fun resetView() {
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)
        wl.setText("")
        hl.setText("")
    }

    fun readValues() {
        val worLoc = findViewById<EditText>(R.id.workLocationEditView)
        val homeLoc = findViewById<EditText>(R.id.homeLocationEditView)
        setPreferences(worLoc.text.toString(), homeLoc.text.toString())
    }

    fun setPreferences(wStr: String, hStr: String) {
        val pref = applicationContext.getSharedPreferences("myPref", 0)
        val editor = pref.edit()
        editor.putString("work_location", wStr)
        editor.putString("home_location", hStr)

        editor.putString("work_location_lat", getLocFromAddress(wStr)?.lat.toString())
        editor.putString("work_location_long", getLocFromAddress(wStr)?.lng.toString())
        editor.putString("home_location_lat", getLocFromAddress(hStr)?.lat.toString())
        editor.putString("home_location_long", getLocFromAddress(hStr)?.lng.toString())
        editor.apply()
        showNotification()
    }

    fun showNotification() {
        val toast = Toast.makeText(applicationContext, "Preferences Saved", Toast.LENGTH_LONG)
        toast.show()
    }

    fun getLocFromAddress(address: String): Lat_Lng? {
        val coder = Geocoder(this)
        try {
            val add: List<Address>? = coder.getFromLocationName(address, 3)
            if (add == null) {
                return null
            }
            val addLoc = add[0]
            return Lat_Lng(addLoc.latitude, addLoc.longitude)
        } catch (e: Exception) {
            println("Error")
            return null
        }
    }
}
