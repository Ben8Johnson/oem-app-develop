package com.wejo.oemapp.fragments.roadside

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentAssistanceBinding
import com.wejo.oemapp.fragments.BaseFragment

/**
 * Created by BenJohnson on 23/10/2017.
 */
class RoadsideAssistanceFragment : BaseFragment() {
    private lateinit var binding: FragmentAssistanceBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_assistance, container, false)
        return binding.root
    }
}