package com.wejo.oemapp.model
import com.google.gson.annotations.SerializedName

data class PhotosItem (@SerializedName("photo_reference")
val photoReference: String = "",
                       val width: Int = 0,
                       @SerializedName("html_attributions")
val htmlAttributions: List<String>? = null,
                       val height: Int = 0)