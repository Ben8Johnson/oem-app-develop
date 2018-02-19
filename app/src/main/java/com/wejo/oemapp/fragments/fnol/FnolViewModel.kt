package com.wejo.oemapp.fragments.fnol

import android.arch.lifecycle.ViewModel
import android.location.Address
import android.location.Location
import android.net.Uri
import com.example.benjohnson.messageviewproject.MessageScript
import com.wejo.oemapp.utils.GeneralUtils

import com.wejo.oemlibrary.fnol.FnolIncidentModel


class FnolViewModel : ViewModel() {
    var description : String? = null
    var address : Address? = null
    var location : Location? = null
    var photos  = ArrayList<Uri>()

    fun buildIncidentModel() : FnolIncidentModel {
        return FnolIncidentModel(
                description,
                GeneralUtils.formatAddress(address!!),
                location?.latitude.toString(),
                location?.longitude.toString(),
                photos)
    }

    fun questionsCompleted(completedAnswers: MessageScript?) {

    }
}