package com.wejo.oemapp.fragments.poi

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.google.android.gms.location.places.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.wejo.oemapp.model.ResultsItem

/**
 * Viewmodel for places of interest fragment
 */
class POIViewModel : ViewModel(), OnMapReadyCallback, PlacesData.IDataReadyCallback, NearbyPlacesAdapter.OnItemClickListener {


    //Places data class
    private var placesData = PlacesData()
    //Googlemap
    lateinit var map: GoogleMap
    //Live data for places
    var places = MutableLiveData<List<ResultsItem>>()

    val showData = ObservableField(false)
    val RADIUS = "5000"


    /**
     * Called when the map has been loaded and is ready for user interaction
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        googleMap.isMyLocationEnabled = true
    }

    /**
     * When a user has selected a place we show the place on the map
     *  @param place the place which the user has selected
     */
    fun placeSelected(place: Place?) {

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(place?.latLng, 14.0f))

    }

    /**
     * User has pressed the find local shops button
     */
    fun findShops() {
        placesData.getNearbyLocationData(map.myLocation.latitude.toString() + "," + map.myLocation.longitude.toString(), RADIUS, "store", this)
    }

    /**
     * User has pressed the find coffee shop button
     */
    fun onCoffeeButtonClicked() {
        placesData.getNearbyLocationData(map.myLocation.latitude.toString() + "," + map.myLocation.longitude.toString(), RADIUS, "cafe", this)
    }

    /**
     * User has pressed the find food button
     */
    fun onFoodBtnClicked() {
        placesData.getNearbyLocationData(map.myLocation.latitude.toString() + "," + map.myLocation.longitude.toString(), RADIUS, "restaurant", this)
    }

    /**
     * User has pressed the find parking button
     */
    fun onParkingBtnClicked() {
        placesData.getNearbyLocationData(map.myLocation.latitude.toString() + "," + map.myLocation.longitude.toString(), RADIUS, "parking", this)
    }

    /**
     * User has pressed the find petrol button
     */
    fun onPetrolBtnClicked() {
        placesData.getNearbyLocationData(map.myLocation.latitude.toString() + "," + map.myLocation.longitude.toString(), RADIUS, "gas_station", this)
    }

    /**
     * Request was succesfull and there are places to show the user
     * @param data is the results from the request
     */
    override fun onDataReady(data: List<ResultsItem>) {
        if (data.isEmpty())
            return
        places.value = data
        showData.set(true)
        showPlacesOnMap()
    }

    /**
     * Closes the list of results
     */
    fun onCloseListClicked() {
        showData.set(false)
    }

    /**
     * When a user selects a place on the list
     */
    override fun onItemClick(position: Int) {
        moveToPlace(places.value!![position])
    }

    /**
     * Moves the camera to a place and adds a pin
     */
    private fun moveToPlace(resultsItem: ResultsItem) {
        map.clear()
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(resultsItem.geometry?.location?.lat!!, resultsItem.geometry.location.lng), 14.0f))
        val marker = MarkerOptions()
        marker.position(LatLng(resultsItem.geometry?.location?.lat!!, resultsItem.geometry.location.lng))
        map.addMarker(marker)
    }


    /**
     * Shows the user all the results on the map
     */
    private fun showPlacesOnMap() {
        for (place in places.value!!) {
            val marker = MarkerOptions()
            marker.position(LatLng(place.geometry?.location?.lat!!, place.geometry.location.lng))
            map.addMarker(marker)
        }
    }


}