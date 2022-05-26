package com.example.data.datamodels.character

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "Character")
@Serializable
data class CharacterModel(
    val created: String,
//    val episode: List<String>,
    val gender: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    @Embedded
    val location: ChLocation,
    val name: String,
    @Embedded
    val origin: ChOrigin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    @Serializable
    data class ChLocation(
        @SerializedName("name")
        val chname: String,
        @SerializedName("url")
        val churl: String
    )

    @Serializable
    data class ChOrigin(
        @SerializedName("name")
        val choname: String,
        @SerializedName("url")
        val chourl: String
    )
}