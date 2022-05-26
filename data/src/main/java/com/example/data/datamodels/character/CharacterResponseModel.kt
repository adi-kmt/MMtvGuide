package com.example.data.datamodels.character

import com.example.data.datamodels.Info

data class CharacterResponseModel(
    val info: Info,
    val results: List<CharacterModel>
)