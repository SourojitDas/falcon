package ie.tcd.scss.ase.utilities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class AppUtils {
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun createAlertNetworkNotFound(context : Context): AlertDialog? {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Network Not Found")
        builder.setMessage("There is No Wifi or Network Connectivity found on your phone. Check Network Settings. " +
                "To have full access to all features of the App please enable Network Connectivity.")
        builder.setNeutralButton("OK"){dialog, which ->
            dialog.dismiss()
        }
        builder.setPositiveButton("GO TO SETTINGS"){dialog, which ->
            startActivity(context,Intent(Settings.ACTION_WIRELESS_SETTINGS),null)
            dialog.dismiss()
        }
        return builder.create()
    }

    private fun createAlertNetworkNotFoundToast(context : Context) {
        Toast.makeText(context,"Network Not Available",Toast.LENGTH_LONG).show()
    }

    private fun checkFirebaseCredentials(){
       val firebaseInstance = FirebaseAuth.getInstance()
        val account = firebaseInstance
    }

}