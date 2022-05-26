package com.example.domain.usecases

import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository

class GetALocationUseCase(
    private val repository: MainRepository
) {
    suspend fun getALocation(name:String): NetworkState<LocationData> =
        TODO("Yet to be implemented")
}