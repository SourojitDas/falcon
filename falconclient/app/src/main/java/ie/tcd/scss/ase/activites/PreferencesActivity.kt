package ie.tcd.scss.ase.activites

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.gson.Gson
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.adapters.PreferenceRecyclerViewAdapter
import ie.tcd.scss.ase.dataclasses.SharedPreferenceDataClass
import ie.tcd.scss.ase.interfaces.ModePreferenceInterface
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper
import java.lang.Exception
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import ie.tcd.scss.ase.dataclasses.Preferences
import java.util.*


class PreferencesActivity : AppCompatActivity(), ModePreferenceInterface {

    private lateinit var preferenceRecyclerView: RecyclerView
    private lateinit var preferencerecycelerViewAdapter: PreferenceRecyclerViewAdapter
    private lateinit var homeLocationFragment: AutocompleteSupportFragment
    private lateinit var workLocationFragment: AutocompleteSupportFragment
    private lateinit var res: Array<String>
    private lateinit var prefModes: ArrayList<Preferences>
    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
//        val wl = findViewById<EditText>(R.id.workLocationEditView)
//        val hl = findViewById<EditText>(R.id.homeLocationEditView)

        sharedPreferenceHelper = SharedPreferenceHelper(applicationContext)
//        val token = AutocompleteSessionToken.newInstance()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.google_maps_key))
        }
//        val placesClient = Places.createClient(this)

//        if (sharedPreferenceHelper.containPreference(getString(R.string.work_loc))) {
//            wl.setText(sharedPreferenceHelper.getPreference(getString(R.string.work_loc)).toString())
//        }
//        if (sharedPreferenceHelper.containPreference(getString(R.string.home_loc))) {
//            hl.setText(sharedPreferenceHelper.getPreference(getString(R.string.home_loc)).toString())
//        }
        homeLocationFragment =
            supportFragmentManager.findFragmentById(R.id.home_location_autocomplete_fragment) as AutocompleteSupportFragment
        homeLocationFragment.setHint("Home Location")
        homeLocationFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        val homeLocationFragmentListener = object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Snackbar.make(
                    window.decorView.findViewById<View>(android.R.id.content),
                    "Error While Fetching Location",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onPlaceSelected(p0: Place) {
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.home_loc),
                        p0.name as String
                    )
                )
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.home_loc_lat),
                        p0.latLng?.latitude.toString()
                    )
                )
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.home_loc_lng),
                        p0.latLng?.longitude.toString()
                    )
                )
            }

        }

        homeLocationFragment.setOnPlaceSelectedListener(homeLocationFragmentListener)


        workLocationFragment =
            supportFragmentManager.findFragmentById(R.id.work_location_autocomplete_fragment) as AutocompleteSupportFragment
        workLocationFragment.setHint("Work Location")
        workLocationFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        val workLocationFragmentListener = object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Snackbar.make(
                    window.decorView.findViewById<View>(android.R.id.content),
                    "Error While Fetching Location",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onPlaceSelected(p0: Place) {
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.work_loc),
                        p0.name as String
                    )
                )
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.work_loc_lat),
                        p0.latLng?.latitude.toString()
                    )
                )
                sharedPreferenceHelper.savePreference(
                    SharedPreferenceDataClass(
                        getString(R.string.work_loc_lng),
                        p0.latLng?.longitude.toString()
                    )
                )
            }

        }

        workLocationFragment.setOnPlaceSelectedListener(workLocationFragmentListener)

        prefModes = ArrayList()
        res = resources.getStringArray(R.array.pref_mode)
        for (key in res) {
            var pref = Preferences(key, false)
//            pref.mode = key
//            pref.selected = false
            prefModes.add(pref)
        }

        //Bind RecyclerView
        preferenceRecyclerView = findViewById<RecyclerView>(R.id.preference_recycle_view)
        preferenceRecyclerView.layoutManager = LinearLayoutManager(this)
        preferencerecycelerViewAdapter = PreferenceRecyclerViewAdapter(prefModes, this, this)
        preferenceRecyclerView.adapter = preferencerecycelerViewAdapter

    }

    data class Lat_Lng(val lat: Double, val lng: Double)

//    fun resetView(view: View) {
//        val wl = findViewById<EditText>(R.id.workLocationEditView)
//        val hl = findViewById<EditText>(R.id.homeLocationEditView)
//        wl.setText("")
//        hl.setText("")
//    }
//
//    fun readValues(view: View) {
//        val worLoc = findViewById<EditText>(R.id.workLocationEditView)
//        val homeLoc = findViewById<EditText>(R.id.homeLocationEditView)
////        val modePref = findViewById<Switch>(R.id.preference_recycle_view)
//        setPreferences(worLoc.text.toString(), homeLoc.text.toString())
//    }
//
//    fun setPreferences(wStr: String, hStr: String) {
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
//
//        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.work_loc), wStr))
//        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.home_loc), hStr))
//        sharedPreferenceHelper.savePreference(
//            SharedPreferenceDataClass(
//                getString(R.string.work_loc_lat),
//                getLocFromAddress(wStr)?.lat.toString()
//            )
//        )
//        sharedPreferenceHelper.savePreference(
//            SharedPreferenceDataClass(
//                getString(R.string.work_loc_lng),
//                getLocFromAddress(wStr)?.lng.toString()
//            )
//        )
//        sharedPreferenceHelper.savePreference(
//            SharedPreferenceDataClass(
//                getString(R.string.home_loc_lat),
//                getLocFromAddress(hStr)?.lat.toString()
//            )
//        )
//        sharedPreferenceHelper.savePreference(
//            SharedPreferenceDataClass(
//                getString(R.string.home_loc_lng),
//                getLocFromAddress(hStr)?.lng.toString()
//            )
//        )
//
//        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.pref_saved), true))

    fun setPreferences(view: View) {

        sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.pref_saved), true))
        showNotification()

        val intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(intent)
        finish()
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


    override fun selectedPrefernceMode(list: ArrayList<Preferences>) {


        Log.d("CHECK", prefModes[0].mode + " : " + prefModes[0].selected)

//        list.forEach {
//            lateinit var saveData:SharedPreferenceDataClass
//            saveData.key = it.mode
//            saveData.value = it.seleceted.toString()
//
//            sharedPreferenceHelper.savePreference(saveData)
//        }


        var gson = Gson()
        var jsonString = gson.toJson(list)
        Log.d("JSON", jsonString)
        var savePreference = SharedPreferenceDataClass(getString(R.string.pref_mode), jsonString)
        sharedPreferenceHelper.savePreference(savePreference)

    }
}