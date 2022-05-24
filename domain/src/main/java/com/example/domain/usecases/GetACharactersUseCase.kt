package com.example.domain.usecases

import com.example.domain.model.CharachterData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository

class GetACharactersUseCase(
    private val repository: MainRepository
) {
    suspend fun getACharacter(name:String):NetworkState<CharachterData> =
        repository.getCharacter(name = name)
}