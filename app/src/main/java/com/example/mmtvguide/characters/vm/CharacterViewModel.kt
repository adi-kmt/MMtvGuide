package com.example.mmtvguide.characters.vm

import android.util.Log
import androidx.lifecycle.Transformations
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
@Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _queryString: MutableStateFlow<String?> = MutableStateFlow("")
    val queryString: StateFlow<String?> get() = _queryString

    private val _listCharacters: MutableStateFlow<PagingData<CharachterData>> = MutableStateFlow(
        PagingData.empty()
    )
    val listCharacters: StateFlow<PagingData<CharachterData>> = _listCharacters

    init {
        viewModelScope.launch {
            _queryString.collect { query ->
                Log.d("Filter Query", query.toString())
                getAllCharactersUseCase.getAllCharacters(query).collectLatest { pagingCh ->
                    _listCharacters.emit(pagingCh)
                }
            }
        }
    }
//
//        }
//    }//Issue with init block, not observing change in query model

//    suspend fun getCharacters(): Flow<PagingData<CharachterData>> =
//        getAllCharactersUseCase.getAllCharacters(null)

//    fun getCharacters() = viewModelScope.launch {
////        Log.d("Filter Query", query.toString())
//        queryString.collectLatest {queryValue->
//            getAllCharactersUseCase.getAllCharacters(queryValue).collect { pagingCh ->
////            Log.d("Filter Query", pagingCh)
//                _listCharacters.emit(pagingCh)
//            }
//        }
//    }

    fun setQuery(query: String?) {
        viewModelScope.launch {
            if (query.isNullOrBlank()){
                _queryString.emit(null)
            }else {
                _queryString.emit(query)
            }

//            queryString.collectLatest { queryValue ->
//                getAllCharactersUseCase.getAllCharacters(queryValue).collectLatest { pagingCh ->
//            Log.d("Filter Query", pagingCh.toString())
//                    Log.d("List Characters", listCharacters.toString())
//                    _listCharacters.emit(pagingCh)
//                }
//            }
        }
    }


//    @OptIn(ExperimentalCoroutinesApi::class)
//    suspend fun getFilteredCharacters(): Flow<PagingData<CharachterData>> =
//        queryString.flatMapLatest { query ->
//            return@flatMapLatest getAllCharactersUseCase.getAllCharacters(query)
//        }

//    suspend fun getFilteredCharacters() {
//        getAllCharactersUseCase.getAllCharacters(queryString.value).collect{
//            _listCharacters.value = it
//        }
//        }
}