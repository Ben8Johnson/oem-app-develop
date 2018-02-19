package com.wejo.oemlibrary.fnol

import android.net.Uri


data class FnolIncidentModel(val description : String?,
                             val address: String?,
                             val latitude: String?,
                             val longitude: String?,
                             @Transient val photos: ArrayList<Uri>)