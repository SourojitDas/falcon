package ie.tcd.scss.ase.interfaces

import ie.tcd.scss.ase.dataclasses.BikeResponse
import ie.tcd.scss.ase.dataclasses.RouteBody
import ie.tcd.scss.ase.dataclasses.RouteResponse
import retrofit2.Call
import retrofit2.http.*

interface RetroFitAPIClient {

    @GET("vls/v1/stations")
    fun getBikeData(
        @Query("contract") city: String,
        @Query("apiKey") apiKey: String
    ): Call<List<BikeResponse>>

    @POST("route")
    fun getRouteDetails(@Body body: RouteBody, @Header("authorization") authKey: String): Call<RouteResponse>


}