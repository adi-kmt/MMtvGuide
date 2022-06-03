package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.api.ApiService
import com.example.data.datamodels.character.CharacterModel
import com.example.data.datamodels.remotekeys.CharacterRemoteKeys
import com.example.data.localstorage.dbs.MainDB
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator
    (
    private val apiService: ApiService,
    private val mainDB: MainDB
)
    :RemoteMediator<Int, CharacterModel>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterModel>
    ): MediatorResult {

        val characterDao = mainDB.characterDao()
        val characterRemoteKeysDao = mainDB.characterRemoteKeysDao()

        try {
            val currentPage:Int = when(loadType){
                LoadType.REFRESH ->{
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1)?:1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = apiService.getPagedCharacter(page = currentPage)
            val endOfPaginationReached = response.body()?.results?.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage-1
            val nextPage = if (endOfPaginationReached == true) null else currentPage+1

            mainDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.deleteAllCharacters()
                    characterRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys: List<CharacterRemoteKeys> = response.body()?.results?.map {
                    CharacterRemoteKeys(
                        id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                } ?: return@withTransaction
                    response.body()?.results?.let { characterDao.insertALLCharacters(it) }
                    characterRemoteKeysDao.addAllRemoteKeys(keys)
                }
            return if (endOfPaginationReached != null && endOfPaginationReached == false){
                MediatorResult.Success(endOfPaginationReached!!)
            }else{
                MediatorResult.Success(true)
            }
            }catch (e: HttpException){
            return MediatorResult.Error(Throwable(e.localizedMessage))
        }catch (e: IOException){
            return MediatorResult.Error(Throwable(e.localizedMessage))
        }
        }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacterModel>)
    : CharacterRemoteKeys? {
         val anchorPosition = state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id}
        return anchorPosition?.let {positionId ->
            mainDB.characterRemoteKeysDao().getRemoteKeys(positionId)
        }
        }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterModel>)
            : CharacterRemoteKeys? {
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {characterModel ->
            mainDB.characterRemoteKeysDao().getRemoteKeys(characterModel.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterModel>)
            : CharacterRemoteKeys? {
        return state.pages.lastOrNull(){
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {characterModel ->
            mainDB.characterRemoteKeysDao().getRemoteKeys(characterModel.id)
        }
    }
    }