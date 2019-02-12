package ie.tcd.scss.ase.activites

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import ie.tcd.scss.ase.R


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var googleSignInButton: SignInButton
    var account: GoogleSignInAccount? = null
    val RC_SIGN_IN = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInButton = findViewById(R.id.googleSignInButton)

        checkSignIn()

        googleSignInButton.setOnClickListener(this);


        // updateUI(account)
    }

    private fun checkSignIn() {
        account = GoogleSignIn.getLastSignedInAccount(this)
        loginCheck()
    }

     private fun loginCheck(): Boolean {


        //Toast.makeText(this,account?.idToken,Toast.LENGTH_LONG)
        if(account != null){
            Toast.makeText(this,account?.id,Toast.LENGTH_LONG)
            return true
        }
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
        if (resultCode == RC_SIGN_IN) {
            val accountData = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignIn(accountData)
        }
    }

    private fun handleSignIn(accountData: Task<GoogleSignInAccount>) {

        try {

            val account = accountData.getResult(ApiException::class.java)
            loginCheck()

        } catch (e: ApiException) {
            loginCheck()
        }

    }

}
