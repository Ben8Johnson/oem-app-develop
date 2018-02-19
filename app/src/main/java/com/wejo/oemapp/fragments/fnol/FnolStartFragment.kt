package com.wejo.oemapp.fragments.fnol

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentFnolStartBinding
import com.wejo.oemapp.fragments.BaseFragment
import com.wejo.oemapp.managers.LocationMgr
import com.wejo.oemapp.utils.GeneralUtils
import kotlinx.android.synthetic.main.fragment_fnol_start.*


class FnolStartFragment : BaseFragment(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    private lateinit var viewModel: FnolViewModel
    private lateinit var locationMgr: LocationMgr
    private lateinit var binding: FragmentFnolStartBinding


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fnol_start, container, false)
        viewModel = ViewModelProviders.of(activity).get(FnolViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMapView(savedInstanceState)
    }

    private fun updateUi() {
        if (viewModel.location != null) {
            //Move the camera to the user's location and zoom in
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(viewModel.location!!.latitude, viewModel.location!!.longitude), 14.0f))
        }
        if (viewModel.address != null && binding.messageView.messageScript == null) {
            binding.messageView.addQuestion(getString(R.string.tv_fnol_opening_message) + GeneralUtils.formatAddress(viewModel.address!!))
            binding.messageView.addQuestion(GeneralUtils.getQuestionsForAccident())
            binding.messageView.currentQuestion.observe(this, Observer<Int> {
                if (it == 1) {
                    binding.mapView.visibility = View.GONE
                }
                if (it == GeneralUtils.getQuestionsForAccident().questions.size) {
                    binding.messageView.addQuestion(getString(R.string.tv_finished_questioning))
                    viewModel.questionsCompleted(binding.messageView.generateFullReport())
                }
            })
        }
    }

    /**
     * Sets up the map and the location manager
     */
    private fun setupMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        locationMgr = LocationMgr(activity, true) { location, address ->
            viewModel.location = location
            viewModel.address = address
            updateUi()
        }
        lifecycle.addObserver(locationMgr)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onMapReady(newMap: GoogleMap?) {
        try {
            map = newMap
            map?.isMyLocationEnabled = true
        } catch (e: SecurityException) {
        }
    }
}
