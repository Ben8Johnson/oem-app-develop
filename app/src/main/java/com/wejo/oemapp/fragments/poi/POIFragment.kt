package com.wejo.oemapp.fragments.poi

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.wejo.oemapp.R
import com.wejo.oemapp.databinding.FragmentPoiBinding
import com.wejo.oemapp.managers.LocationMgr
import com.wejo.oemapp.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_poi.*


/**
 * @class POIFragment  is a fragment which shows the user local places of interest
 */
class POIFragment : Fragment(), View.OnClickListener {



    private lateinit var binding: FragmentPoiBinding
    private lateinit var viewModel : POIViewModel
    private val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    private lateinit var nearbyPlacesAdapter : NearbyPlacesAdapter
    private lateinit var locationMgr : LocationMgr


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_poi, container, false)
        viewModel = ViewModelProviders.of(this).get(POIViewModel::class.java)
        binding.viewModel = viewModel
        binding.btnSearch.setOnClickListener(this)
        return binding.root
    }



    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mv.onCreate(savedInstanceState)
        mv.getMapAsync(viewModel)
        binding.executePendingBindings()
        binding.placesRv.layoutManager = LinearLayoutManager(context)
        nearbyPlacesAdapter = NearbyPlacesAdapter(arrayListOf(), viewModel)
        binding.placesRv.adapter = nearbyPlacesAdapter
        viewModel.places.observe(this, Observer<List<ResultsItem>> {
            it?.let {
                nearbyPlacesAdapter.replaceData(it)
            }
        })
        locationMgr = LocationMgr(activity, true) {
            location, address ->
            viewModel.map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 14.0f))
        }
    }

    override fun onClick(v: View?) {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity)
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(activity, data)
                viewModel.placeSelected(place)
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(activity, data)

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mv.onResume()
        if (locationMgr != null)
            locationMgr.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (!mv.isActivated)
            mv.onPause()
        if (locationMgr != null)
            locationMgr.onResume()
    }


}