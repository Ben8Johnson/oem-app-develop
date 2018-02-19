package com.wejo.oemapp.fragments.myrewards

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentMyrewardsBinding
import com.wejo.oemapp.fragments.BaseFragment

/**
 * Created by BenJohnson on 06/12/2017.
 */
class MyRewardsFragment : BaseFragment() {

    private lateinit var binding: FragmentMyrewardsBinding
    private lateinit var viewModel: MyRewardsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myrewards, container, false)
        viewModel = ViewModelProviders.of(this).get(MyRewardsViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}