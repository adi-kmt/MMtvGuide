package com.example.domain.usecases

data class GetLocationUseCase(
    val singleLocationUseCase: GetALocationUseCase,
    val multipleLocationsUseCase: GetAllLocationsUseCase
)
