package com.wejo.oemapp.api

import com.wejo.oemapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by BenJohnson on 24/01/2018.
 */
interface WeatherApIClient {

    /**
     * Gets nearby places
     */
    @GET("/data/2.5/weather?lat={lat}&lon={lon}")
    fun getCurrentTemp(@Path("lat") lat: String, @Path("long") long: String): Call<WeatherResponse>
}