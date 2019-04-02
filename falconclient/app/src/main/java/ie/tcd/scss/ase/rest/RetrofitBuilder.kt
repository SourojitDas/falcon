package ie.tcd.scss.ase.rest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    fun retrofitBuilder(baseURL: String): Retrofit {
//        System.out.println(baseURL)
//        var retrofitBuilder = Retrofit.Builder()
//            .baseUrl(baseURL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
        return Retrofit.Builder()
            .baseUrl(baseURL+"")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}