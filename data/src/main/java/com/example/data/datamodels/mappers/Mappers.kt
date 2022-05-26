package com.example.data.datamodels.mappers

import com.example.data.datamodels.character.CharacterModel
import com.example.data.datamodels.location.LocationModel
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData

fun LocationModel.toLocationData():LocationData{
    return LocationData(
        id = id,
        name = name,
        created = created,
        dimension = dimension,
        type = type
    )
}

fun CharacterModel.toCharachterData():CharachterData{
    return CharachterData(
        gender = gender,
        id = id,
    image= image,
    name= name,
    species=species,
    status=status,
    type=type
    )
}