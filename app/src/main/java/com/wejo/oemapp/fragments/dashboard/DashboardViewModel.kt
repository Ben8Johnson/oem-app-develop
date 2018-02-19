package com.wejo.oemapp.fragments.dashboard

import android.arch.lifecycle.ViewModel
import com.wejo.oemapp.model.ResultsItem

/**
 * Created by BenJohnson on 24/01/2018.
 */
class DashboardViewModel : ViewModel(), DashboardData.IDataReadyCallback {


    private val data = DashboardData()
    fun viewCreated() {
        populateTemperature()
    }

    private fun populateTemperature() {
        data.requestCurrentTemp(this)
    }

    override fun onDataReady(data: List<ResultsItem>) {
    }





}