package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

//    suspend fun getCharacter(name:String):

    suspend fun getAllCharacters():Flow<PagingData<CharachterData>>

//    suspend fun getLocation(name:String):NetworkState<LocationData>

    suspend fun getAllLocations():Flow<PagingData<LocationData>>
}