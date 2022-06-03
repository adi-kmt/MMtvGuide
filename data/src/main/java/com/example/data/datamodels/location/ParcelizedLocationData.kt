package com.example.data.datamodels.location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedLocationData(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val type: String,
    val url: String
):Parcelable