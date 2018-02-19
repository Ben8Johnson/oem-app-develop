package com.wejo.oemapp.activities.rewards

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.wejo.oemapp.R
import com.wejo.oemapp.activities.BaseActivity
import com.wejo.oemapp.activities.main.MainVpAdapter
import com.wejo.oemapp.databinding.ActivityRewardsBinding
import com.wejo.oemapp.fragments.drivingscore.DrivingScoreFragment
import com.wejo.oemapp.fragments.myrewards.MyRewardsFragment
import com.wejo.oneapp.utils.SimpleBackStackManager

/**
 * @class RewardsActivity is a class used for holding the rewards based fragments
 */
class RewardsActivity : BaseActivity() {

    private lateinit var binding: ActivityRewardsBinding
    private lateinit var backStackManger: SimpleBackStackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rewards)
        backStackManger = SimpleBackStackManager(R.id.fl_main, supportFragmentManager)
        setupViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    /**
     * Populates the viewpager with an adapter containing the MyRewardsFragment(), DrivingScoreFragment()
     */
    private fun setupViewPager(viewPager: ViewPager) {
        val pages: MutableList<Fragment> = mutableListOf(MyRewardsFragment(), DrivingScoreFragment())
        val pageTitles: MutableList<String> = mutableListOf("Rewards", "My Driving score")
        val adapter = MainVpAdapter(supportFragmentManager, pages, pageTitles)
        viewPager.adapter = adapter
    }
}