package com.wejo.oemapp.fragments.account

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentAccountBinding
import com.wejo.oemapp.fragments.BaseFragment

/**
 * @class AccountFragment
 *
 * SimpleFragment displaying the users account details
 */
class AccountFragment : BaseFragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
//        viewModel = ViewModelProviders.of(this).get(MyDetailsViewModel::class.java)
//        binding.viewModel = viewModel
        return binding.root
    }
}
