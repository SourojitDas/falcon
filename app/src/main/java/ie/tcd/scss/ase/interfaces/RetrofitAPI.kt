package ie.tcd.scss.ase.interfaces

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface RetrofitAPI {

@GET("/directions")
fun getBikeData(city: String, api_key: String):String
companion object {
    fun create():RetrofitAPI{
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl("https://api.jcdecaux.com/vls/v1/stations?")
            .build()

        return retrofit.create(RetrofitAPI::class.java)

    }
}
}

