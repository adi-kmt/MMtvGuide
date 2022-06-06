package com.example.data.api

import javax.inject.Inject

class ApiServiceImpl
    @Inject constructor(private val apiService: ApiService) {

    suspend fun getPagedCharacter(page:Int) = apiService.getPagedCharacter(page)

    suspend fun getPagedLocation(page:Int) = apiService.getPagedLocation(page)
}