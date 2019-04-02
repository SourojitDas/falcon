package ie.tcd.scss.ase

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.firebase.auth.FirebaseAuth
import com.google.maps.android.PolyUtil
import ie.tcd.scss.ase.activites.PreferencesActivity
import ie.tcd.scss.ase.interfaces.RetroFitAPIClient
import ie.tcd.scss.ase.poko.RouteResponse
import ie.tcd.scss.ase.rest.RetrofitBuilder
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false

    private lateinit var map: GoogleMap

    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val PLACE_PICKER_REQUEST = 3

    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var directionSearchButton: MaterialButton
    private lateinit var sourceLatLng: LatLng
    private lateinit var destinationLatLng: LatLng


    // 1
    private lateinit var locationCallback: LocationCallback
    // 2
    private lateinit var locationRequest: LocationRequest

    private var locationUpdateState = false


    private lateinit var sourceLocationFragment: AutocompleteSupportFragment
    private lateinit var destinationLocationFragment: AutocompleteSupportFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        sharedPreferenceHelper = SharedPreferenceHelper(applicationContext)

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.google_maps_key))
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        val intent = Intent(this, PreferencesActivity::class.java).apply {
//        }
//        startActivity(intent)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                Log.d("LAST LOCATION", lastLocation.latitude.toString())
//                if (::map.isInitialized) {
                placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
//                }
            }
        }

        sourceLocationFragment =
            supportFragmentManager.findFragmentById(R.id.source_location_autocomplete_fragment) as AutocompleteSupportFragment
        sourceLocationFragment.setHint("Source Location")
        sourceLocationFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        val sourceLocationFragmentListener = object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Snackbar.make(
                    window.decorView.findViewById<View>(android.R.id.content),
                    "Error While Fetching Location",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onPlaceSelected(p0: Place) {
                sourceLatLng = p0.latLng as LatLng
            }

        }

        sourceLocationFragment.setOnPlaceSelectedListener(sourceLocationFragmentListener)


        destinationLocationFragment =
            supportFragmentManager.findFragmentById(R.id.destination_location_autocomplete_fragment) as AutocompleteSupportFragment
        destinationLocationFragment.setHint("Destination Location")
        destinationLocationFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        val destinationLocationFragmentListener = object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Snackbar.make(
                    window.decorView.findViewById<View>(android.R.id.content),
                    "Error While Fetching Location",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onPlaceSelected(p0: Place) {
                destinationLatLng = p0.latLng as LatLng
            }

        }

        destinationLocationFragment.setOnPlaceSelectedListener(destinationLocationFragmentListener)



        directionSearchButton = findViewById(R.id.directionSearchButton)
        directionSearchButton.setOnClickListener {
            if (::sourceLatLng.isInitialized && ::destinationLatLng.isInitialized) {
                getRoute(sourceLatLng, destinationLatLng)
            } else {
                Snackbar.make(
                    window.decorView.findViewById<View>(android.R.id.content),
                    "Set Source and Destination",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        createLocationRequest()

        /* val fab = findViewById<FloatingActionButton>(R.id.fab)
         fab.setOnClickListener {
             loadPlacePicker()
         }*/
    }

    private fun getRoute(sourceLatLng: LatLng, destinationLatLng: LatLng) {

        var firebaseApp = FirebaseAuth.getInstance()
        firebaseApp.currentUser!!.getIdToken(true).addOnCompleteListener {
            if (it.isSuccessful) {
                getRouteFromNetwork(sourceLatLng, destinationLatLng, it.result?.token)
            } else {

            }
        }


    }

    private fun getRouteFromNetwork(sourceLatLng: LatLng, destinationLatLng: LatLng, token: String?) {


        var baseURL = "http://34.248.131.131/"
        var retrofitBuilder = RetrofitBuilder.retrofitBuilder(baseURL.trim())
        var retroFitAPIClient = retrofitBuilder.create(RetroFitAPIClient::class.java)

        var routeResponseCall = retroFitAPIClient.getRouteDetails("Bearer " + (token as String))

        routeResponseCall.enqueue(object : Callback<RouteResponse> {
            override fun onFailure(call: Call<RouteResponse>, t: Throwable) {

                Log.d("RETROFIT : ", t.localizedMessage)
                Snackbar.make(
                    window.decorView.findViewById(android.R.id.content),
                    "Unable to fetch Route.",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("RETRY", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        getRouteFromNetwork(sourceLatLng, destinationLatLng, token)
                    }

                }).show()
            }

            override fun onResponse(call: Call<RouteResponse>, response: Response<RouteResponse>) {
                Log.d("RESPONESE ", response.body()!!.geocodeMembers!![0]?.geocoderStatus)
                Toast.makeText(applicationContext, "GOT RESPONSE", Toast.LENGTH_LONG).show()
                drawRoute(response.body() as RouteResponse)
            }

        })

    }

    override fun onMapReady(googleMap: GoogleMap) {
//        map = googleMap
//
//        val dublin = LatLng(53.3498, 6.2603)
//        map.addMarker(MarkerOptions().position(dublin).title("Marker in Dublin"))
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dublin, 12.0f))
//        map.uiSettings.isZoomControlsEnabled = true
//        map.setOnMarkerClickListener(this)
//        setUpMap()

        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        setUpMap()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        // 3
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                sourceLatLng = currentLatLng
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng) {
        // 1
        val markerOptions = MarkerOptions().position(location)

        val titleStr = getAddress(location)  // add these two lines
        markerOptions.title(titleStr)
        // 2
        map.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0..address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

    private fun startLocationUpdates() {
        //1
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        //2
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        // 1
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(
                        this@MapsActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    // 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }
    }

    // 2
    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // 3
    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }


    private fun loadPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(this@MapsActivity), PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mapmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.setting_menu -> {
                startActivity(Intent(applicationContext, PreferencesActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun drawRoute(response: RouteResponse) {

        val path: MutableList<List<LatLng>> = ArrayList()
        val routes = response.routes!!
        val legs = routes[0]?.legs!!
        val steps = legs[0]?.steps!!

        for (i in 0 until steps.size) {
            val points = steps[i]?.polyline?.points!!
            path.add(PolyUtil.decode(points))
        }

        for (i in 0 until path.size) {
            var polyline = PolylineOptions().jointType(JointType.ROUND)
            val pattern = Arrays.asList(
                Dot(), Gap(20f), Dash(30f), Gap(20f)
            )
            val pattern1 = Arrays.asList(
                Dot(), Gap(0f)
            )
            var size = 30f
            var mapColor = Color.BLUE


//            polyline.pattern(PATTERN_POLYLINE_DOTTED)
            polyline.color(mapColor)
            polyline.width(size)


            this.map.addPolyline(polyline.addAll(path[i]).geodesic(true))
        }
    }

}