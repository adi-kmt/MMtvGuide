package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.api.ApiService
import com.example.data.datamodels.location.LocationModel
import com.example.data.datamodels.remotekeys.LocationRemoteKeys
import com.example.data.localstorage.dbs.MainDB
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator
    (
    private val apiService: ApiService,
    private val mainDB: MainDB
): RemoteMediator<Int, LocationModel>() {
    override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, LocationModel>
): MediatorResult {

    val locationDao = mainDB.locationDao()
    val locationRemoteKeysDao = mainDB.locationRemoteKeysDao()

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

        val response = apiService.getPagedLocation(page = currentPage)
        val endOfPaginationReached = response.body()?.results?.isEmpty()

        val prevPage = if (currentPage == 1) null else currentPage-1
        val nextPage = if (endOfPaginationReached == true) null else currentPage+1

        mainDB.withTransaction {
            if (loadType == LoadType.REFRESH) {
                locationDao.deleteAllLocations()
                locationRemoteKeysDao.deleteAllRemoteKeys()
            }

            val keys: List<LocationRemoteKeys> = response.body()?.results?.map {
                LocationRemoteKeys(
                    id = it.id,
                    prevPage = prevPage,
                    nextPage = nextPage
                )
            } ?: return@withTransaction
            response.body()?.results?.let { locationDao.insertALLLocations(it) }
            locationRemoteKeysDao.addAllRemoteKeys(keys)
        }

        //            return endOfPaginationReached?.let { MediatorResult.Success(endOfPaginationReached = it)}
        return MediatorResult.Success(endOfPaginationReached!!) //TODO("To be fixed")
    }catch (e: HttpException){
        return MediatorResult.Error(e)
    }catch (e: IOException){
        return MediatorResult.Error(e)
    }
}


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, LocationModel>)
            : LocationRemoteKeys? {
        val anchorPosition = state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id}
        return anchorPosition?.let {positionId ->
            mainDB.locationRemoteKeysDao().getRemoteKeys(positionId)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, LocationModel>)
            : LocationRemoteKeys? {
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {locationModel ->
            mainDB.locationRemoteKeysDao().getRemoteKeys(locationModel.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, LocationModel>)
            : LocationRemoteKeys? {
        return state.pages.lastOrNull(){
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {locationModel ->
            mainDB.locationRemoteKeysDao().getRemoteKeys(locationModel.id)
        }
    }
}