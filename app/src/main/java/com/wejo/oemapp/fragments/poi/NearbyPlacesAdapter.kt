package com.wejo.oemapp.fragments.poi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wejo.oemapp.R.layout.rv_item_place
import com.wejo.oemapp.databinding.RvItemPlaceBinding
import com.wejo.oemapp.model.ResultsItem

/**
 * @class NearbyPlacesAdapter is a simple recyclerview adapter for displaying nearby poi
 */
class NearbyPlacesAdapter(private var items: List<ResultsItem>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<NearbyPlacesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvItemPlaceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemPlaceBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(resultsItem: ResultsItem, listener: OnItemClickListener?) {
            binding.resultItem = resultsItem
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }

            binding.executePendingBindings()
        }
    }

    fun  replaceData(it: List<ResultsItem>){
        items = it
        notifyDataSetChanged()
    }


}