package com.wejo.oemapp.activities

import android.support.v7.app.AppCompatActivity
import com.wejo.oneapp.utils.SimpleBackStackManager

/**
 * @class BaseActivity
 * Abstract activity with methods often used in fragments
 */
abstract class BaseActivity : AppCompatActivity() {

    //Backstack manger for handling fragments
    var backStackManager : SimpleBackStackManager? = null

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            backStackManager?.goBack()
        }
        // if we are on the root fragment (or there are no fragments on the stack) allow the base class
        // to handle the back button as we are at the root of the stack
        if (supportFragmentManager.backStackEntryCount <= 1) {
            super.onBackPressed()
        }
    }
}