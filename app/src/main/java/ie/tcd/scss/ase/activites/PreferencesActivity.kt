package ie.tcd.scss.ase.activites

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.gson.Gson
import ie.tcd.scss.ase.MapsActivity
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.adapters.PreferenceRecyclerViewAdapter
import ie.tcd.scss.ase.poko.PreferedMode
import ie.tcd.scss.ase.poko.SharedPreferenceDataClass
import ie.tcd.scss.ase.utilities.ModePreferenceInterface
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper
import java.lang.Exception

open class PreferencesActivity : AppCompatActivity(), ModePreferenceInterface {

    private lateinit var preferenceRecyclerView: RecyclerView
    private lateinit var preferencerecycelerViewAdapter: PreferenceRecyclerViewAdapter
    private lateinit var res: Array<String>
    private lateinit var prefModes: ArrayList<PreferedMode>
    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var sharedPreferenceDataClass: SharedPreferenceDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        val wl = findViewById<EditText>(R.id.workLocationEditView)
        val hl = findViewById<EditText>(R.id.homeLocationEditView)

        val sharedPref = getSharedPreferences(getString(R.string.pref_name), 0)

        sharedPreferenceHelper = SharedPreferenceHelper(sharedPref)

        if (sharedPreferenceHelper.containPreference(getString(R.string.work_loc))) {
            wl.setText(sharedPreferenceHelper.getPreference(getString(R.string.work_loc)).toString())
        }
        if (sharedPreferenceHelper.containPreference(getString(R.string.home_loc))) {
            hl.setText(sharedPreferenceHelper.getPreference(getString(R.string.home_loc)).toString())
        }

        prefModes = ArrayList()
        res = resources.getStringArray(R.array.pref_mode)
        for (key in res) {
            var pref = PreferedMode()
            pref.mode = key
            pref.seleceted = false
            prefModes.add(pref)
        }

        //Bind RecyclerView
        preferenceRecyclerView = findViewById<RecyclerView>(R.id.preference_recycle_view)
        preferenceRecyclerView.layoutManager = LinearLayoutManager(this)
        preferencerecycelerViewAdapter = PreferenceRecyclerViewAdapter(prefModes, this, this)
        preferenceRecyclerView.adapter = preferencerecycelerViewAdapter

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
//        val modePref = findViewById<Switch>(R.id.preference_recycle_view)
        setPreferences(worLoc.text.toString(), homeLoc.text.toString())
    }

    fun setPreferences(wStr: String, hStr: String) {
//        val pref = applicationContext.getSharedPreferences(getString(R.string.pref_name), 0)
//        val editor = pref.edit()
//        editor.putString(getString(R.string.work_loc), wStr)
//        editor.putString(getString(R.string.home_loc), hStr)
//
//        editor.putString(getString(R.string.work_loc_lat), getLocFromAddress(wStr)?.lat.toString())
//        editor.putString(getString(R.string.work_loc_lng), getLocFromAddress(wStr)?.lng.toString())
//        editor.putString(getString(R.string.home_loc_lat), getLocFromAddress(hStr)?.lat.toString())
//        editor.putString(getString(R.string.home_loc_lng), getLocFromAddress(hStr)?.lng.toString())
//        editor.apply()

        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.work_loc), wStr))
        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.home_loc), hStr))
        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.work_loc_lat), getLocFromAddress(wStr)?.lat.toString()))
        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.work_loc_lng), getLocFromAddress(wStr)?.lng.toString()))
        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.home_loc_lat), getLocFromAddress(hStr)?.lat.toString()))
        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.home_loc_lng), getLocFromAddress(hStr)?.lng.toString()))

        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.pref_saved),true))

        showNotification()

        val intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(intent)
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


    override fun selectedPrefernceMode(list: ArrayList<PreferedMode>) {


        Log.d("CHECK", prefModes[0].mode + " : " + prefModes[0].seleceted)

//        list.forEach {
//            lateinit var saveData:SharedPreferenceDataClass
//            saveData.key = it.mode
//            saveData.value = it.seleceted.toString()
//
//            sharedPreferenceHelper.savePreference(saveData)
//        }


        var gson = Gson()
        var jsonString = gson.toJson(list)
        Log.d("JSON",jsonString)
        var savePreference = SharedPreferenceDataClass(getString(R.string.pref_mode),jsonString)
        sharedPreferenceHelper.savePreference(savePreference)

    }
}
