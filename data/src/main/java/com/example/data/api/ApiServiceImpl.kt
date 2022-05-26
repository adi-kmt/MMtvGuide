package com.example.data.api

import javax.inject.Inject

class ApiServiceImpl
    @Inject constructor(private val apiService: ApiService) {

    suspend fun getPagedCharacter(page:Int) = apiService.getPagedCharacter(page)

    suspend fun getPagedLocation(page:Int) = apiService.getPagedLocation(page)

    suspend fun getPagedCharacterParticular(page: Int, chName:String) =
        apiService.getPagedCharacterParticular(page, chName)

    suspend fun getPagedLocationParticular(page: Int, locName:String) =
        apiService.getPagedLocationParticular(page, locName)
}