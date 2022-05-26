package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.model.CharachterData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase
    @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun getAllCharacters(): Flow<PagingData<CharachterData>> =
        repository.getAllCharacters()
}