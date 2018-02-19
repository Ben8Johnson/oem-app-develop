package com.wejo.oemapp.fragments.mydetails

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wejo.oemapp.R
import com.wejo.oemapp.activities.main.MainActivity
import com.wejo.oemapp.databinding.FragmentMyDetailsBinding
import com.wejo.oemapp.fragments.BaseFragment
import com.wejo.oemapp.fragments.account.AccountFragment

/**
 * @class MyDetailsFragment  is a fragment where the user can view their details and details about their car
 */
class MyDetailsFragment : BaseFragment(), View.OnClickListener {


    private lateinit var binding: FragmentMyDetailsBinding
    private lateinit var viewModel: MyDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_details, container, false)
        viewModel = ViewModelProviders.of(this).get(MyDetailsViewModel::class.java)
//        binding.viewModel = viewModel
        binding.rlMyAccount.setOnClickListener(this)
        return binding.root
    }


    override fun onClick(v: View?) {
        (activity as MainActivity).backStackManger.goTo(AccountFragment(), R.anim.slide_in_up, R.anim.slide_out_up)
    }


}