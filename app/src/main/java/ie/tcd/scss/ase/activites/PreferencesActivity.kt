package ie.tcd.scss.ase.activites

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ie.tcd.scss.ase.R

open class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)
        val sharedPref = getSharedPreferences("myPref", 0)
        if (sharedPref.contains(getString(R.string.work_location))) {
            wl.setText(sharedPref.getString(getString(R.string.work_location), ""))
        }
        if (sharedPref.contains(getString(R.string.home_location))) {
            hl.setText(sharedPref.getString(getString(R.string.home_location), ""))
        }
    }

    data class Lat_Lng(val lat: Double, val lng: Double)

    fun resetView(view: View) {
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)
        wl.setText("")
        hl.setText("")
    }

    fun readValues(view: View) {
        val worLoc = findViewById<EditText>(R.id.workLocationEditView)
        val homeLoc = findViewById<EditText>(R.id.homeLocationEditView)
        setPreferences(worLoc.text.toString(), homeLoc.text.toString())
    }

    fun setPreferences(wStr: String, hStr: String) {
        val pref = applicationContext.getSharedPreferences("myPref", 0)
        val editor = pref.edit()
        editor.putString(getString(R.string.work_location), wStr)
        editor.putString(getString(R.string.home_location), hStr)

        editor.putString(getString(R.string.work_location_lat), getLocFromAddress(wStr)?.lat.toString())
        editor.putString(getString(R.string.work_location_long), getLocFromAddress(wStr)?.lng.toString())
        editor.putString(getString(R.string.home_location_lat), getLocFromAddress(hStr)?.lat.toString())
        editor.putString(getString(R.string.home_location_long), getLocFromAddress(hStr)?.lng.toString())
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
