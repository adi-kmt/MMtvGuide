package com.example.data.repository

import androidx.paging.*
import com.example.data.api.ApiService
import com.example.data.datamodels.mappers.toCharachterData
import com.example.data.datamodels.mappers.toLocationData
import com.example.data.localstorage.dbs.MainDB
import com.example.data.paging.CharacterRemoteMediator
import com.example.data.paging.LocationRemoteMediator
import com.example.domain.model.CharachterData
import com.example.domain.model.LocationData
import com.example.domain.model.NetworkState
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepoImpl
    @Inject constructor(
        private val apiService: ApiService,
        private val mainDB: MainDB
    )
    :MainRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getAllCharacters(): Flow<PagingData<CharachterData>> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = CharacterRemoteMediator(apiService, mainDB),
            pagingSourceFactory = {mainDB.characterDao().getPagedCharacters()}
        ).flow.map {pagedCharacter ->
        pagedCharacter.map {character ->
            character.toCharachterData()
        }
        }
    }


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getAllLocations(): Flow<PagingData<LocationData>> {
        val pagingSourceFactory = {mainDB.locationDao().getPagedLocations()}
        return Pager(
            config = PagingConfig(20),
            remoteMediator = LocationRemoteMediator(apiService, mainDB),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map {pagedLocation ->
        pagedLocation.map {location->
            location.toLocationData()
        }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getCharacter(query:String):Flow<PagingData<CharachterData>>{
        return Pager(
            config = PagingConfig(20),
            remoteMediator = CharacterRemoteMediator(apiService, mainDB),
            pagingSourceFactory = {mainDB.characterDao().getPagedCharacters()}
        ).flow.map {pagedCharacter ->
            pagedCharacter.map {character ->
                character.toCharachterData()
            }.filter {
                it.name == query
            }
        }
    }
}