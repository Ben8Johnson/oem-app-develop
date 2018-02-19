package com.wejo.oemapp.activities.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @class MainVpAdapter Simple viewpager adapter for displaying fragments in a viewpager
 */
class MainVpAdapter(supportFragmentManager: FragmentManager, var pages: MutableList<Fragment>, var pageTitles: MutableList<String>) : FragmentPagerAdapter(supportFragmentManager) {


    override fun getItem(position: Int): Fragment {
        return pages.get(position)
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles.get(position)
    }

}