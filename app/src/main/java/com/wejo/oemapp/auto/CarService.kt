package com.wejo.oemapp.auto

import com.google.android.apps.auto.sdk.CarActivity
import com.google.android.apps.auto.sdk.CarActivityService

/**
 * Created by BenJohnson on 24/10/2017.
 */
class CarService : CarActivityService() {
    override fun getCarActivity(): Class<out CarActivity> {
        return MainCarActivity::class.java
    }


}