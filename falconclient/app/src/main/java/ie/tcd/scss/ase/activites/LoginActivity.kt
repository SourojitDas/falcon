package ie.tcd.scss.ase.activites

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import ie.tcd.scss.ase.R
import com.google.firebase.auth.GoogleAuthProvider
import ie.tcd.scss.ase.dataclasses.SharedPreferenceDataClass
import ie.tcd.scss.ase.utilities.SharedPreferenceHelper


class LoginActivity : AppCompatActivity(), View.OnClickListener, OnCompleteListener<AuthResult> {


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
            .requestIdToken(getString(R.string.web_config_id))
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        googleSignInButton = findViewById(R.id.googleSignInButton)

        firebaseAuth = FirebaseAuth.getInstance()
        checkSignIn()

        googleSignInButton.setOnClickListener(this)


        // updateUI(account)
    }

    private fun checkSignIn() {
        /*val pref = getApplicationContext().getSharedPreferences("myPref", 0)
       val is_logged_in= pref.getBoolean(getString(R.string.is_logged_in), false)
       val firebase_id = pref.getString(getString(R.string.firebase_id),null)
        if(is_logged_in.equals(true) && firebase_id!=null){
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }else{*/
        account = GoogleSignIn.getLastSignedInAccount(this)
        loginCheck()
        //}

    }

    private fun loginCheck(): Boolean {
        //Toast.makeText(this,account?.idToken,Toast.LENGTH_LONG)
        if (account != null) {

            val sharedPreferenceHelper = SharedPreferenceHelper(applicationContext)

            val dataList = ArrayList<SharedPreferenceDataClass>()


            //Log.e("login-debug", account?.idToken)
            Log.e("login-debug", account?.id)
            Log.e("login-debug", account?.email)
            Log.e("login-debug", account?.idToken)

            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)

            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this)


            //    Log.d("UID", firebaseAuth.currentUser?.uid as String)

            if (account != null) {
                val user = firebaseAuth.currentUser
                dataList.add(SharedPreferenceDataClass(getString(R.string.name), account?.displayName as String))
                dataList.add(SharedPreferenceDataClass(getString(R.string.firebase_id), account?.id as String))
                dataList.add(SharedPreferenceDataClass(getString(R.string.email), account?.email as String))
                val status = sharedPreferenceHelper.savePreference(dataList)
                return status
            }

            return false
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
            googleSignInButton.isClickable = false
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
                    Snackbar.make(
                        window.decorView.findViewById(android.R.id.content),
                        "Trying to Login",
                        Snackbar.LENGTH_LONG
                    ).show()

//                    Toast.makeText(getApplicationContext(), "Preferences Saved", Toast.LENGTH_LONG)
                } else {
                    Snackbar.make(
                        window.decorView.findViewById(android.R.id.content),
                        "Login Failed",
                        Snackbar.LENGTH_LONG
                    ).show()

                    googleSignInButton.isClickable = true
                }

            } else {
                googleSignInButton.isClickable = true
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG)
            }
//            Log.e("Account ID", account.displayName)
////            Log.e("Account ID", account.idToken)
//            loginCheck()

        } catch (e: ApiException) {
            Log.e("api", e.toString())
        }

    }

    override fun onComplete(task: Task<AuthResult>) {
        val result = task.result
        if (task.isSuccessful) {
            val user = firebaseAuth.currentUser
            user?.getIdToken(true)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.token as String
                    Log.d("UID : ", user.uid)

                    val sharedPreferenceHelper = SharedPreferenceHelper(applicationContext)


                    sharedPreferenceHelper.savePreference(
                        SharedPreferenceDataClass(
                            getString(R.string.idtoken),
                            it.result?.token as String
                        )
                    )
                    sharedPreferenceHelper.savePreference(
                        SharedPreferenceDataClass(
                            getString(R.string.is_logged_in),
                            true
                        )
                    )
                    sharedPreferenceHelper.savePreference(
                        SharedPreferenceDataClass(
                            getString(R.string.user_id),
                            user.uid
                        )
                    )

//            sharedPreferenceHelper.savePreference(SharedPreferenceDataClass(getString(R.string.idtoken),user?. as String))

                    val intent = Intent(this, PreferencesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        } else {

            Snackbar.make(window.decorView.findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG)
                .show()
            googleSignInButton.isClickable = true

        }
    }


}
