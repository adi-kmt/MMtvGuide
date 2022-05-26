package com.example.data.datamodels.location

import com.example.data.datamodels.Info

data class LocationResponseModel(
    val info: Info,
    val results: List<LocationModel>
)