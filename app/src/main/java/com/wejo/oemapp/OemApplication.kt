package com.wejo.oemapp

import android.app.Application
import com.wejo.oemlibrary.OemLibrary


class OemApplication : Application() {

    companion object {
        val oemLibrary: OemLibrary by lazy {
            OemLibrary.instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        OemLibrary.initialise(this)
    }
}