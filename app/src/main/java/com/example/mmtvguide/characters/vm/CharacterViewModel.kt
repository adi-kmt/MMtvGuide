package com.example.mmtvguide.characters.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.CharachterData
import com.example.domain.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
    @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase):ViewModel() {

        suspend fun getCharacters(): Flow<PagingData<CharachterData>> =
            getAllCharactersUseCase.getAllCharacters()
}