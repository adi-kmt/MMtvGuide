package com.example.data.localstorage.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodels.location.LocationModel

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLLocations(characterList:List<LocationModel>)

    @Query("SELECT * FROM Location")
    fun getPagedLocations(): PagingSource<Int, LocationModel>

    @Query("DELETE FROM Location")
    fun deleteAllLocations()
}