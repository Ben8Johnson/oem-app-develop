package com.wejo.oemapp.firebase

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * @class MyFirebaseInstanceIDService
 * Firebase instance for starting fcm service
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {


    private val TAG = "MyFirebaseIIDService"

    override fun onTokenRefresh() {

        //Getting registration token
        val refreshedToken = FirebaseInstanceId.getInstance().token

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)

    }

    private fun sendRegistrationToServer(token: String) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}