package com.example.mmtvguide.locations.vm

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.model.LocationData
import com.example.domain.usecases.GetAllLocationsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationViewModel
@Inject constructor(private val getAllLocationsUseCase: GetAllLocationsUseCase):ViewModel(){

    suspend fun getLocation():Flow<PagingData<LocationData>> =
        getAllLocationsUseCase.getAllLocations()
}