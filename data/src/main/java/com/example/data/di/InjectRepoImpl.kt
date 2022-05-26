package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.localstorage.dbs.MainDB
import com.example.data.repository.MainRepoImpl
import com.example.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InjectRepoImpl {

    @Provides
    fun providesRepoImpl(apiService: ApiService, mainDB: MainDB): MainRepository =
        MainRepoImpl(apiService, mainDB)
}