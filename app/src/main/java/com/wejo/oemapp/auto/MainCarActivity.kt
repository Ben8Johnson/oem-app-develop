package com.wejo.oemapp.auto

import android.os.Bundle
import android.util.Log
import com.google.android.apps.auto.sdk.CarActivity
import com.google.android.apps.auto.sdk.DayNightStyle
import com.wejo.oemapp.R

/**
 * Created by BenJohnson on 24/10/2017.
 *
 *  Activity holding the car controls
 */
class MainCarActivity : CarActivity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        //Setup view and create
        Log.d("car activity", "Car activity started")
        setTheme(android.R.style.Theme_Material_Light_Dialog_NoActionBar)
        setContentView(R.layout.activity_car_main)
        carUiController.statusBarController.c()
        carUiController.statusBarController.setDayNightStyle(DayNightStyle.FORCE_NIGHT)
    }

}