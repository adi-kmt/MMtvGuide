package com.example.domain.usecases

data class GetCharacterUseCase(
    val singleCharacterUseCase: GetACharactersUseCase,
    val multipleCharacterUseCase: GetAllCharactersUseCase
)
