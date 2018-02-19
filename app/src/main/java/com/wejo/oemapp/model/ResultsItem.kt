package com.wejo.oemapp.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(val reference: String = "",
                       val types: List<String>? = null,
                       val scope: String = "",
                       val icon: String = "",
                       val name: String = "",
                       @SerializedName("opening_hours")
                       val openingHours: OpeningHours? = null,
                       val rating: Double = 0.0,
                       val geometry: Geometry? = null,
                       val vicinity: String = "",
                       val id: String = "",
                       val photos: List<PhotosItem>? = null,
                       @SerializedName("place_id")
                       val placeId: String = "")