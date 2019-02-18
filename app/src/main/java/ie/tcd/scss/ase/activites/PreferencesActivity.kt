package ie.tcd.scss.ase.activites

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ie.tcd.scss.ase.R
import java.lang.Exception

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences);
        var wl = findViewById<EditText>(R.id.workLocationEditView);
        var hl = findViewById<EditText>(R.id.homeLocationEditView);
        val sharedPref = getSharedPreferences("myPref", 0)
        if (sharedPref.contains(getString(R.string.wLoc))) {
            wl.setText(sharedPref.getString(getString(R.string.wLoc), ""));
        }
        if (sharedPref.contains(getString(R.string.hLoc))) {
            hl.setText(sharedPref.getString(getString(R.string.hLoc), ""));
        }
    }


    data class Lat_Lng(val lat: Double, val lng: Double)

    fun reset(view: View) {
        var wl = findViewById<EditText>(R.id.workLocationEditView);
        var hl = findViewById<EditText>(R.id.homeLocationEditView);
        wl.setText("");
        hl.setText("");
    }

    fun setPref(view: View) {
        val worLoc = findViewById<EditText>(R.id.workLocationEditView);
        val homeLoc = findViewById<EditText>(R.id.homeLocationEditView);
        val pref = getApplicationContext().getSharedPreferences("myPref", 0);
        val editor = pref.edit();
        val wStr = worLoc.text.toString();
        val hStr = homeLoc.text.toString();
        editor.putString(getString(R.string.wLoc), wStr);
        editor.putString(getString(R.string.wLoc_Lat), getLocFromAddress(wStr)?.lat.toString());
        editor.putString(getString(R.string.wLoc_Lng), getLocFromAddress(wStr)?.lng.toString());
        editor.putString(getString(R.string.hLoc), hStr)
        editor.putString(getString(R.string.hLoc_Lat), getLocFromAddress(hStr)?.lat.toString());
        editor.putString(getString(R.string.hLoc_Lng), getLocFromAddress(hStr)?.lng.toString())
        editor.commit();
        val toast = Toast.makeText(applicationContext, "Preferences Saved", Toast.LENGTH_LONG);
        toast.show();
    }

    fun getLocFromAddress(address: String): Lat_Lng? {
        val coder = Geocoder(this);
        try {
            val add = coder.getFromLocationName(address, 3);
            if (add == null) {
                return null;
            }
            val add_loc = add[0];
            return Lat_Lng(add_loc.latitude, add_loc.longitude)
        } catch (e: Exception) {
            println("Error")
            return null;
        }
    }
}
