package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAllCharacters(characterName:String?):Flow<PagingData<CharachterData>>

    suspend fun getAllLocations():Flow<PagingData<LocationData>>
}