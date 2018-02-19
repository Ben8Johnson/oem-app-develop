package com.wejo.oemlibrary.comms

import android.content.Context
import android.net.Uri
import com.wejo.commslibrary.requestmanagers.RequestManager
import com.wejo.commslibrary.requests.IRequest
import com.wejo.commslibrary.requests.RequestObserver
import com.wejo.commslibrary.requests.body.BinaryFileBody
import com.wejo.oemlibrary.fnol.FnolIncidentModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.io.File

/**
 * Created by BenJohnson on 23/10/2017.
 */

class Comms(val context: Context) {

    private lateinit var retrofit: Retrofit
    private lateinit var requestManager: RequestManager

    companion object {
        val BASE_URL = ""
        const val X_API_KEY = ""
        val X_API_KEY_VAL = ""
        const val AUTH_HEADER = ""
        val AUTH_HEADER_VAL = "=="
        //All removed for demo purposes
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        requestManager = RequestManager
                .buildVolleyRequestManager(context)
    }

    fun upload(model: FnolIncidentModel): Call<Unit> {
        return retrofit.create(FnolUpload::class.java).uploadFnolDetails(model, AUTH_HEADER_VAL, X_API_KEY_VAL)
    }

    fun uploadImage(uri: Uri, observer: RequestObserver<String>) {
        val request = requestManager.buildRequest(String::class.java)
        request.url = ""
        request.isAuthenticated = false
        request.addHeader(AUTH_HEADER, AUTH_HEADER_VAL)
        request.addHeader(X_API_KEY, X_API_KEY_VAL)
        request.setVerb(IRequest.HttpMethod.POST)
        val body = BinaryFileBody(File(uri.path), "application/zip")
        request.setBody(body)
        request.setObserver(observer)
        requestManager.addToQueue(request)
    }

    interface FnolUpload {
        @POST("upload")
        fun uploadFnolDetails(@Body fnolIncidentModel: FnolIncidentModel,
                              @Header(AUTH_HEADER) authHeader: String,
                              @Header(X_API_KEY) apiKey: String): Call<Unit>
    }
}
