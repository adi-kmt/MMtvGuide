package com.example.domain.model

data class LocationData(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String,
)