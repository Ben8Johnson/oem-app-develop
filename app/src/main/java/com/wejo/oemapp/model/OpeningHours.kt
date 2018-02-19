package com.wejo.oemapp.model
import com.google.gson.annotations.SerializedName

data class OpeningHours (@SerializedName("open_now")
val openNow: Boolean = false)