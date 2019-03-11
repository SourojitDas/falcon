package ie.tcd.scss.ase.activites

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.interfaces.RetroFitAPIClient
import ie.tcd.scss.ase.rest.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        displayText()
    }

    fun displayText(){

        val baseURL:String="https://api.jcdecaux.com"
        var retrofitBuilder = RetrofitBuilder.retrofitBuilder(baseURL)
        var textBox = findViewById<EditText>(R.id.editText)
        val retroFitAPIClient = retrofitBuilder.create(RetroFitAPIClient::class.java)
        GlobalScope.launch(Dispatchers.Default) {
            val responseCall = retroFitAPIClient.getBikeData("Dublin", "ed91f65214a826cb97c5444a15f25665726b95ae")
            try{

                val res = responseCall.await()
                textBox.setText(res[0].address)
            } catch (e: Exception){
                println("Bike API Error")
                textBox.setText("Bike API Error")
            }
        }
    }

}
