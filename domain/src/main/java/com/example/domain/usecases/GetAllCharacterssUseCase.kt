package com.example.domain.usecases

import com.example.domain.model.CharachterData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository

class GetAllCharactersUseCase(
    private val repository: MainRepository
) {
    suspend fun getAllCharacters(): NetworkState<List<CharachterData>> =
        repository.getAllCharacters()
}