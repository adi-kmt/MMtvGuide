package com.example.mmtvguide.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import com.example.mmtvguide.characters.adapter.CharacterListAdapter
import com.example.mmtvguide.databinding.LocationItemBinding

class LocationListadapter
    :PagingDataAdapter<LocationData, LocationListadapter.LocationListHolder>(DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationListHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationListHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {item ->
            holder.bind(item)
        }
    }


    class LocationListHolder(private val binding: LocationItemBinding):RecyclerView.ViewHolder(binding.root){

//        init {
//            binding.root.setOnClickListener {
//                val position = bindingAdapterPosition
//                val item = getPosition()
//            }
//        }


        fun bind(locationData: LocationData){
            binding.apply {
                locationText.text = locationData.name
            }
        }
    }

    class DiffCall:DiffUtil.ItemCallback<LocationData>() {
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
            oldItem == newItem
    }
}