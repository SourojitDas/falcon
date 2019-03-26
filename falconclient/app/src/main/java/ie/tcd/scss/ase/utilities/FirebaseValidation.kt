package ie.tcd.scss.ase.utilities

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ie.tcd.scss.ase.R

class FirebaseValidation {
    private fun checkFirebaseCredentials(context: Context): Boolean {
        val firebaseInstance = FirebaseAuth.getInstance()
        val account = firebaseInstance.currentUser
        val sharedPreferenceHelper = SharedPreferenceHelper(context)
        if (sharedPreferenceHelper.containPreference(context.getString(R.string.user_id))) {
            val userID = account?.uid as String
            if (!userID.equals(sharedPreferenceHelper.getPreference(context.getString(R.string.user_id)))) {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show()
                return false
            } else return true
        } else {
            Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show()
            return false
        }

    }
}