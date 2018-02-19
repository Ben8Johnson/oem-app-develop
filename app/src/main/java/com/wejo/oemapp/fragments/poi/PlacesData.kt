package com.wejo.oemapp.fragments.poi

import com.wejo.oemapp.api.PlacesApiClient
import com.wejo.oemapp.model.GooglePlacesResponse
import com.wejo.oemapp.model.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Data class to handle places data
 */
class PlacesData {
    /**
     * api client for requests concerning places
     */
    private var placesApiClient: PlacesApiClient

    /**
     * Default constructor
     */
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        placesApiClient = retrofit.create(PlacesApiClient::class.java)
    }

    /**
     * Makes a request to obtain data about nearby places of interest
     */
    fun getNearbyLocationData(location: String, radius: String, types: String, dataReadyCallback: IDataReadyCallback) {

        val map = HashMap<String,String>()
        map.put("location",location)
        map.put("radius",radius)
        map.put("type",types)
        map.put("key", "AIzaSyD2oKP89WY3yMW3jt8_WnGSunEakhpPBIE")
        placesApiClient.search(map).enqueue(object : Callback<GooglePlacesResponse> {

            override fun onResponse(call: Call<GooglePlacesResponse>, response: Response<GooglePlacesResponse>) {
                val result = response.body() as GooglePlacesResponse
                dataReadyCallback.onDataReady(result.results!!)
            }

            override fun onFailure(call: Call<GooglePlacesResponse>, t: Throwable) {

            }
        })
    }


    /**
     * Callback for when data is available
     */
    interface IDataReadyCallback {
        fun onDataReady(data: List<ResultsItem>)
    }

}