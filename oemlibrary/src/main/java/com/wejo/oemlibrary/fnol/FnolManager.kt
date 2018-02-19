package com.wejo.oemlibrary.fnol

import android.content.Context
import com.wejo.commslibrary.requests.IRequest
import com.wejo.commslibrary.requests.RequestObserver
import com.wejo.oemlibrary.OemLibrary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FnolManager {

    fun uploadIncident(context: Context, model: FnolIncidentModel, includeJourney: Boolean = true) {
        if (includeJourney) {
            OemLibrary.uploadJourney(context)
        }
        OemLibrary.instance.comms.upload(model).enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }
        })

        for (uri in model.photos) {
            OemLibrary.instance.comms.uploadImage(uri, object : RequestObserver<String> {
                override fun requestComplete(request: IRequest<String>) {
                }

                override fun requestFailed(request: IRequest<String>) {
                }
            })
        }
    }

}
