package com.example.data.datamodels.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "LocationRemoteKeys")
data class LocationRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val prevPage: Int?,
    val nextPage: Int?
)
