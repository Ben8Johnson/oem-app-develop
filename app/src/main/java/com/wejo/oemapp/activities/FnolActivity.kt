package com.wejo.oemapp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import com.wejo.oemapp.ImageTakenListener
import com.wejo.oemapp.R
import com.wejo.oemapp.fragments.fnol.FnolStartFragment
import com.wejo.oemapp.utils.Constants
import com.wejo.oneapp.utils.SimpleBackStackManager
import java.io.FileNotFoundException
import java.io.IOException

/**
 * @class FnolActivity
 *
 * Activity for holding FNOL chat fragments
 */
class FnolActivity : BaseActivity() {

    //Listener to pass the image from the intent filter through to the fragment
    private lateinit var imageTakenListener: ImageTakenListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fnol)
        backStackManager = SimpleBackStackManager(R.id.frame_layout, supportFragmentManager)
        backStackManager?.goTo(FnolStartFragment(), true)


        supportActionBar?.setTitle(R.string.title_fnol)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * sets a listener on the activity to receive images taken by the user
     *
     * TODO: Needs changing as we shouldn't have a view setting listeners on the activity need to
     * create a new solution
     */
    fun setImageRecivedListener(imageTakenListener: ImageTakenListener) {
        this.imageTakenListener = imageTakenListener
    }

    /**
     * Received when the user has taken a photo or uploaded an image from the gallery
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Constants.TAKE_PHOTO_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            imageTakenListener.imageTaken(data?.extras?.get("data") as Bitmap)
        }
        //Detects request codes
        if (requestCode == Constants.PHOTO_FROM_GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImage = data?.data
            var bitmap: Bitmap? = null
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                imageTakenListener.imageTaken(bitmap)
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
    }
}