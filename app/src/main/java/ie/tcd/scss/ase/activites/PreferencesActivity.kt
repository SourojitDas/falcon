package ie.tcd.scss.ase.activites

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.Toast
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.adapters.PreferenceRecyclerViewAdapter
import java.lang.Exception

open class PreferencesActivity : AppCompatActivity() {

    private lateinit var preferenceRecyclerView:RecyclerView
    private lateinit var preferencerecycelerViewAdapter: PreferenceRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)
        val sharedPref = getSharedPreferences(getString(R.string.pref_name), 0)
        if (sharedPref.contains(getString(R.string.work_loc))) {
            wl.setText(sharedPref.getString(getString(R.string.work_loc), ""))
        }
        if (sharedPref.contains(getString(R.string.home_loc))) {
            hl.setText(sharedPref.getString(getString(R.string.home_loc), ""))
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
        val pref = applicationContext.getSharedPreferences(getString(R.string.pref_name), 0)
        val editor = pref.edit()
        editor.putString(getString(R.string.work_loc), wStr)
        editor.putString(getString(R.string.home_loc), hStr)

        editor.putString(getString(R.string.work_loc_lat), getLocFromAddress(wStr)?.lat.toString())
        editor.putString(getString(R.string.work_loc_lng), getLocFromAddress(wStr)?.lng.toString())
        editor.putString(getString(R.string.home_loc_lat), getLocFromAddress(hStr)?.lat.toString())
        editor.putString(getString(R.string.home_loc_lng), getLocFromAddress(hStr)?.lng.toString())
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
