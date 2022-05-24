package com.example.mmtvguide.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import com.example.mmtvguide.characters.adapter.CharacterListAdapter
import com.example.mmtvguide.databinding.LocationItemBinding

class LocationListadapter:ListAdapter<LocationData, LocationListadapter.LocationListHolder>(DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationListHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationListHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class LocationListHolder(private val binding: LocationItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(locationData: LocationData){
            binding.apply {
                // bind image
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