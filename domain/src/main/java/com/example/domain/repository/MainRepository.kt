package com.example.domain.repository

import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import com.example.domain.model.NetworkState

interface MainRepository {

    suspend fun getCharacter(name:String):NetworkState<CharachterData>

    suspend fun getAllCharacters():NetworkState<List<CharachterData>>

    suspend fun getLocation(name:String):NetworkState<LocationData>

    suspend fun getAllLocations():NetworkState<List<LocationData>>
}