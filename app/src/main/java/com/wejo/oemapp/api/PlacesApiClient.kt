package com.wejo.oemapp.api

import com.wejo.oemapp.model.GooglePlacesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @interface PlacesApiClient
 *
 * Retrofit API client for accessing google places api
 */
interface PlacesApiClient {

    /**
     * Gets nearby places
     */
    @GET("place/nearbysearch/json")
    fun search(
            @QueryMap options : Map<String, String>
    ): Call<GooglePlacesResponse>
}