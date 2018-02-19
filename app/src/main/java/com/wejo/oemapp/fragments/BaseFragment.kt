package com.wejo.oemapp.fragments

import android.support.v4.app.Fragment
import com.wejo.oemapp.activities.BaseActivity


abstract class BaseFragment : Fragment() {

    protected val baseActivity: BaseActivity
        get() = activity as BaseActivity

    fun goTo(fragment: BaseFragment) {
        baseActivity.backStackManager?.goTo(fragment)
    }

}