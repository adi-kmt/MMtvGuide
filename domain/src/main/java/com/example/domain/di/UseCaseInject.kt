package com.example.domain.di

import com.example.domain.repository.MainRepository
import com.example.domain.usecases.GetAllCharactersUseCase
import com.example.domain.usecases.GetAllLocationsUseCase
import com.example.domain.usecases.GetLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseInject {

    @Provides
    fun providesAllLocationUseCase(repository: MainRepository):GetAllLocationsUseCase =
        GetAllLocationsUseCase(repository = repository)

    @Provides
    fun providesAllCharacterUseCase(repository: MainRepository):GetAllCharactersUseCase =
        GetAllCharactersUseCase(repository = repository)
}