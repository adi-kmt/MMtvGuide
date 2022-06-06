package com.example.mmtvguide.characters.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.model.CharachterData
import com.example.domain.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
@Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _queryString: MutableStateFlow<String> = MutableStateFlow("")
    val queryString: StateFlow<String> get() = _queryString

    suspend fun getCharacters(): Flow<PagingData<CharachterData>> =
        getAllCharactersUseCase.getAllCharacters(null)

    fun setQuery(query: String) {
        _queryString.value = query
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getFilteredCharacters(): Flow<PagingData<CharachterData>> =
        queryString.flatMapLatest { query ->
            return@flatMapLatest getAllCharactersUseCase.getAllCharacters(query)
        }
}