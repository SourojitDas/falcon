package ie.tcd.scss.ase.utilities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.location.LocationManager


class LocationHardwareEssentials {

    private fun isGPSAvailable(context: Context): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gps_enabled = false
        var network_enabled = false

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }


        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }

        if (gps_enabled && network_enabled) {
            return true
        }
        return false
    }

    private fun createAlertLocationNotEnabled(context: Context): AlertDialog? {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Network Not Found")
        builder.setMessage(
            "Location Service or GPS is not enabled. Please turn on GPS or Location service to have full access to the " +
                    "application.")
        builder.setNeutralButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setPositiveButton("GO TO SETTINGS") { dialog, _ ->
            ContextCompat.startActivity(context, Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), null)
            dialog.dismiss()
        }
        return builder.create()
    }

    private fun createAlertNetworkNotFoundToast(context: Context) {
        Toast.makeText(context, "Network Not Available", Toast.LENGTH_LONG).show()
    }

}