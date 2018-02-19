package com.wejo.oemapp.managers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.AsyncTask
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

/**
 * @class LocationMgr
 * handles the location manager with the activity lifecycle
 */
class LocationMgr(context : Context, reverseGeoCodeLocation : Boolean = false,
                  callbackFunc: (location : Location, address : Address?) -> Unit) :
        LifecycleObserver, GoogleApiClient.ConnectionCallbacks, LocationListener {

    var googleApi : GoogleApiClient
    var connected = false
    var currentLocation : Location? = null
    var callback : (location : Location, address : Address?) -> Unit
    var reverseGeoCode = false
    val context : Context
    var geocoder : GeocoderTask? = null
    var waitingForConnection = false

    init {
        callback = callbackFunc
        reverseGeoCode = reverseGeoCodeLocation
        this.context = context
        googleApi = GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build()
        googleApi.connect()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        val request = LocationRequest()
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        request.interval = 1000
        request.smallestDisplacement = 0.0f
        try {
            if (connected) {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApi, request, this)
            }
            else {
                waitingForConnection = true
            }
        } catch (e: SecurityException){}
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApi, this)
    }

    override fun onConnected(p0: Bundle?) {
        connected = true
        if (waitingForConnection) {
            onResume()
            waitingForConnection = false
        }
    }

    override fun onConnectionSuspended(var1: Int) {

    }

    override fun onLocationChanged(location : Location) {
        currentLocation = location
        if (currentLocation!!.accuracy < 200) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApi, this)
            if (reverseGeoCode) {
                geocoder?.cancel(true)
                geocoder = GeocoderTask()
                geocoder?.execute(currentLocation)

            }
            else {
                callback.invoke(location, null)
            }
        }
    }

    inner class GeocoderTask() : AsyncTask<Location, Void, Address>() {

        override fun doInBackground(vararg params: Location): Address? {
            var geocoder = Geocoder(context)
            val addresses = geocoder.getFromLocation(params.first().latitude, params.first().longitude, 1)
            if (addresses.count() > 0) {
                return addresses.first()
            }
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()
            // ...
        }

        override fun onPostExecute(result: Address?) {
            super.onPostExecute(result)
            callback.invoke(currentLocation!!, result)
            geocoder = null
        }
    }
}