package com.wejo.oemapp.activities.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.wejo.oemapp.R
import com.wejo.oemapp.activities.BaseActivity
import com.wejo.oemapp.activities.FnolActivity
import com.wejo.oemapp.activities.rewards.RewardsActivity
import com.wejo.oemapp.databinding.ActivityMainBinding
import com.wejo.oemapp.fragments.carcontrol.CarControlFragment
import com.wejo.oemapp.fragments.dashboard.DashboardFragment
import com.wejo.oemapp.fragments.mydetails.MyDetailsFragment
import com.wejo.oemapp.fragments.poi.POIFragment
import com.wejo.oemapp.fragments.roadside.RoadsideAssistanceFragment
import com.wejo.oemapp.utils.PermissionsUtils
import com.wejo.oneapp.utils.SimpleBackStackManager

/**
 * @class MainActivity used to hold a view pager containing the majority of the apps contents
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var backStackManger: SimpleBackStackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        PermissionsUtils.requestPermissions(this)
        backStackManger = SimpleBackStackManager(R.id.fl_main, supportFragmentManager)
        setupViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    /**
     * Populates the viewpager with an adapter containing the DashboardFragment, MyDetailsFragment and POIFragment
     */
    private fun setupViewPager(viewPager: ViewPager) {
        val pages: MutableList<Fragment> = mutableListOf(DashboardFragment(), MyDetailsFragment(), POIFragment())
        val pageTitles: MutableList<String> = mutableListOf(getString(R.string.dashboard_title), getString(R.string.details_title), getString(R.string.poi_title))
        val adapter = MainVpAdapter(supportFragmentManager, pages, pageTitles)
        viewPager.adapter = adapter
    }

    /**
     * When the user clicks the car remote button they will be taken to the CarControlFragment
     */
    fun onCarRemoteClicked(view: View) {
        backStackManger.goTo(CarControlFragment(), R.anim.slide_in_up, R.anim.slide_out_up)
    }

    /**
     * When the user clicks the accident button they will be taken to FnolActivity
     */
    fun onAccidentClicked(view: View) {
        startActivity(Intent(this, FnolActivity::class.java))
    }

    /**
     * When the usser clicks the roadside assistence button they will be taken to RoadsideAssistanceFragment
     */
    fun onAssistanceClicked(view: View) {
        backStackManger.goTo((RoadsideAssistanceFragment()), R.anim.slide_in_up, R.anim.slide_out_up)

    }

    /**
     * When the user clicks the rewards button they will be taken to the RewardsActivity
     */
    fun onMyRewardsClicked(view: View) {
        startActivity(Intent(this, RewardsActivity::class.java))

    }
}
