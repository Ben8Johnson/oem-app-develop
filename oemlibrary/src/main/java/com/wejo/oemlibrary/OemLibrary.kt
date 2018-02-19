package com.wejo.oemlibrary

import android.content.Context
import com.wejo.oemlibrary.comms.Comms
import com.wejo.oemlibrary.fnol.FnolManager
import com.wejosdk.internal.api.InternalSdkErrorCode
import com.wejosdk.internal.api.WejoSdk
import com.wejosdk.internal.api.WejoSdkListener

/**
 * Created by BenJohnson on 11/10/2017.
 */

class OemLibrary(val context : Context)  {

   val fnolManager by lazy {
        FnolManager()
    }

    internal val comms by lazy {
        Comms(context)
    }

    private val listener = object : WejoSdkListener {
        override fun startupComplete() {
        }
        override fun registrationComplete() {
        }
        override fun startupFailed(errorCode: InternalSdkErrorCode) {
        }
        override fun alreadyRunning() {
        }
    }

    init {
        //Propper sdk init removed due to security and not wanting to release keys
    }

    companion object {

        lateinit var instance: OemLibrary

        fun initialise(context: Context) {
            instance = OemLibrary(context)
        }

        fun uploadJourney(context: Context) {
            WejoSdk.getInstance().forceJourneyUpload(context)
        }
    }
}