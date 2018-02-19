package com.wejo.oemapp.fragments.dashboard

import com.wejo.oemapp.model.ResultsItem

/**
 * @class DashboardData
 *
 * data class for dashboard fragment
 */
class DashboardData {

    fun requestCurrentTemp(iDataReadyCallback: IDataReadyCallback) {

    }

    /**
     * Callback for when data is available
     */
    interface IDataReadyCallback {
        fun onDataReady(data: List<ResultsItem>)
    }
}