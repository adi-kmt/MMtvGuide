package com.example.mmtvguide.locations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.domain.model.LocationData
import com.example.mmtvguide.R
import com.example.mmtvguide.commonui.ViewBindingKotlinModel
import com.example.mmtvguide.databinding.FragmentLocationListBinding
import com.example.mmtvguide.databinding.LocationItemBinding
import com.example.mmtvguide.databinding.LocationItemHeaderBinding
import dagger.hilt.android.qualifiers.ApplicationContext

class LocationListController(
): PagingDataEpoxyController<LocationData>() {


    override fun buildItemModel(currentPosition: Int, item: LocationData?): EpoxyModel<*> {


        item?.let {
            return LocationListEpoxyModel(
                locationData = it
            ).id("Location ${it.id}")
        }?: return LocationListEpoxyModel(locationData =
        LocationData("", "", 0, "", "", "")).id("Location 0")
    }


    data class LocationListEpoxyModel(
        val locationData: LocationData
    ):ViewBindingKotlinModel<LocationItemBinding>(R.layout.location_item) {
        override fun LocationItemBinding.bind() {
            locationText.text = locationData.name
        }
    }
}