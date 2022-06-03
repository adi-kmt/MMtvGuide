package com.example.data.datamodels.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedCharachterData(
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
):Parcelable