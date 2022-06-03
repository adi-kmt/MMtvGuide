package com.example.data.datamodels.mappers

import com.example.data.datamodels.character.CharacterModel
import com.example.data.datamodels.character.ParcelizedCharachterData
import com.example.data.datamodels.location.LocationModel
import com.example.data.datamodels.location.ParcelizedLocationData
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData

fun LocationModel.toLocationData():LocationData{
    return LocationData(
        id = id,
        name = name,
        created = created,
        dimension = dimension,
        type = type,
        url = url
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
    type=type,
        url = url
    )
}

fun CharachterData.toParcelizedCharachterData():ParcelizedCharachterData{
    return ParcelizedCharachterData(
        gender = gender,
        id = id,
        image= image,
        name= name,
        species=species,
        status=status,
        type=type,
        url = url
    )
}

fun LocationData.toParcelizedLocationData():ParcelizedLocationData{
    return ParcelizedLocationData(
        id = id,
        name = name,
        created = created,
        dimension = dimension,
        type = type,
        url = url
    )
}