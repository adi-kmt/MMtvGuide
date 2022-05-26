package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.model.LocationData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocationsUseCase
    @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun getAllLocations(): Flow<PagingData<LocationData>> =
        repository.getAllLocations()
}