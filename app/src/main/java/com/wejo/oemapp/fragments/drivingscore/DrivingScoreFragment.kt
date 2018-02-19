package com.wejo.oemapp.fragments.drivingscore

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentDrivingScoreBinding
import com.wejo.oemapp.fragments.BaseFragment

/**
 * @class DrivingScoresFragment
 *
 * To be implemented but should show information about the users driving
 *
 * TODO: Everything
 */
class DrivingScoreFragment : BaseFragment() {

    private lateinit var binding: FragmentDrivingScoreBinding
    private lateinit var viewModel: DrivingScoreViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_driving_score, container, false)
        viewModel = ViewModelProviders.of(this).get(DrivingScoreViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}