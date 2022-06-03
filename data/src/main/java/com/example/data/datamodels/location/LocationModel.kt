package com.example.data.datamodels.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "Location")
@Serializable
data class LocationModel(
    val created: String,
    val dimension: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
//    val residents: List<Any>,
    val type: String,
    val url: String
)