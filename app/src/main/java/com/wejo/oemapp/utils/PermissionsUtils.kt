package com.wejo.oemapp.utils

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.wejo.oemapp.R
import java.util.*


/**
 * Created by BenJohnson on 06/10/2017.
 */


class PermissionsUtils {

    companion object  {
        private  val APP_PERMISSIONS = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        )
        private val PERMISSION_REQUEST_CODE = 123
        private var activity : Activity? = null
        private var dialogShown = false

        fun requestPermissions(activity : Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!allPermissionsGranted(activity)) {
                    this.activity = activity
                    requestAppPermissions()
                }
            }
        }

        private fun allPermissionsGranted(activity : Activity): Boolean {
            for (permission in APP_PERMISSIONS) {
                if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private fun requestAppPermissions() {
            val deniedPermissions = ArrayList<String>()
            for (permission in APP_PERMISSIONS) {
                if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission)
                }
            }
            if (deniedPermissions.size > 0) {
                activity!!.requestPermissions(deniedPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
            }
            else {
                activity = null
            }
        }

        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            if (!allPermissionsGranted(activity!!)) {
                // Tell user to accept permissions
                val alertDialog = AlertDialog.Builder(activity!!).create()
                alertDialog.setTitle(activity!!.getString(R.string.dialog_title_permissions))
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity!!.getString(R.string.dialog_ok),
                    DialogInterface.OnClickListener { dialog,
                         _ ->
                            dialog.dismiss()
                            if (!dialogShown) {
                                dialogShown = true
                                requestAppPermissions()
                            }
                            else {
                                val intent = Intent()
                                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                val uri = Uri.fromParts("package", activity!!.packageName, null)
                                intent.data = uri
                                activity?.startActivity(intent)
                            }

                        }
                )
                alertDialog.show()
            }
        }
    }
}