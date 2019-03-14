package ie.tcd.scss.ase.interfaces

import ie.tcd.scss.ase.poko.BikeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroFitAPIClient {

    @GET("vls/v1/stations")
    fun getBikeData(@Query("contract") city: String,
                    @Query ("apiKey") apiKey: String): Call<List<BikeResponse>>


}