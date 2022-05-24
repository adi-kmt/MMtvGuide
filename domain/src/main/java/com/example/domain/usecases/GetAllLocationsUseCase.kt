package com.example.domain.usecases

import com.example.domain.model.LocationData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository

class GetAllLocationsUseCase (
    private val repository: MainRepository
) {
    suspend fun getAllLocations(): NetworkState<List<LocationData>> =
        repository.getAllLocations()
}