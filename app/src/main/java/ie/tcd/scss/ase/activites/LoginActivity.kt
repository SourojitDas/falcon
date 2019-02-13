package ie.tcd.scss.ase.activites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ie.tcd.scss.ase.MapsActivity
import ie.tcd.scss.ase.R


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var googleSignInButton: SignInButton
    lateinit var firebaseAuth: FirebaseAuth
    var account: GoogleSignInAccount? = null
    val RC_SIGN_IN = 1001

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        googleSignInButton = findViewById(R.id.googleSignInButton)

        firebaseAuth = FirebaseAuth.getInstance()
        checkSignIn()

        googleSignInButton.setOnClickListener(this);


        // updateUI(account)
    }

    private fun checkSignIn() {
        val pref = getApplicationContext().getSharedPreferences("myPref", 0)
       val is_logged_in= pref.getBoolean(getString(R.string.is_logged_in), false)
       val firebase_id = pref.getString(getString(R.string.firebase_id),null)
        if(is_logged_in.equals(true) && firebase_id!=null){
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }else{
            account = GoogleSignIn.getLastSignedInAccount(this)
            loginCheck()
        }

    }

    private fun loginCheck(): Boolean {
        //Toast.makeText(this,account?.idToken,Toast.LENGTH_LONG)
        if (account != null) {
            val pref = getApplicationContext().getSharedPreferences("myPref", 0)

            val editor = pref.edit()
            //Log.e("login-debug", account?.idToken)
            Log.e("login-debug", account?.id)
            Log.e("login-debug", account?.email)
            editor.putBoolean(getString(R.string.is_logged_in), true)
            editor.putString(getString(R.string.name), account?.displayName)
            //editor.putString(getString(R.string.token), account?.idToken)
            editor.putString(getString(R.string.firebase_id), account?.id)
            editor.putString(getString(R.string.email), account?.email)
            val status = editor.commit()
            Log.e("login-debug", status.toString())
            return status
//            Toast.makeText(this,account?.id,Toast.LENGTH_LONG)
//            return true
        }
        Log.e("login-debug", "False")
        return false
    }


    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onClick(v: View?) {
        if (v == googleSignInButton) {
            signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val accountData = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignIn(accountData)
        }
    }

    private fun handleSignIn(accountData: Task<GoogleSignInAccount>) {

        try {

            account = accountData.getResult(ApiException::class.java)!!
            if (account != null && account?.id != null) {
                // Snackbar.make(window.decorView.rootView, "Login Successfull", Snackbar.LENGTH_LONG).show()
                if (loginCheck()) {
                    Snackbar.make(window.decorView.rootView, "Login Successful", Snackbar.LENGTH_LONG).show()

                    val intent = Intent(this, PreferencesActivity::class.java)
                    startActivity(intent)

//                    Toast.makeText(getApplicationContext(), "Preferences Saved", Toast.LENGTH_LONG)
                } else {
                    Snackbar.make(window.decorView.rootView, "Login Failed", Snackbar.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
            }
//            Log.e("Account ID", account.displayName)
////            Log.e("Account ID", account.idToken)
//            loginCheck()

        } catch (e: ApiException) {
            Log.e("api", e.toString())
        }

    }


}
