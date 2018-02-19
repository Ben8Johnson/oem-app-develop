package com.wejo.oemapp.fragments.carcontrol

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentCarControlBinding
import com.wejo.oemapp.fragments.BaseFragment

/**
 * @class CarControlFragment
 *
 * Simple Fragment allowing the user to remotely control their car
 */
class CarControlFragment : BaseFragment() {
    private lateinit var binding: FragmentCarControlBinding
    private lateinit var viewModel: CarControlViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_control, container, false)
        viewModel = ViewModelProviders.of(this).get(CarControlViewModel::class.java)
        return binding.root
    }
}