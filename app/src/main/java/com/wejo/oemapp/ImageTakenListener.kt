package com.wejo.oemapp

import android.graphics.Bitmap

/**
 * Created by BenJohnson on 22/11/2017.
 */
interface ImageTakenListener {

    fun imageTaken(bitmap: Bitmap)
}