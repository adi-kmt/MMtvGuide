package com.example.data.localstorage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodels.remotekeys.LocationRemoteKeys

@Dao
interface LocationRemoteKeysDao {
    @Query("SELECT * FROM LocationRemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): LocationRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<LocationRemoteKeys>)

    @Query("DELETE FROM LocationRemoteKeys")
    suspend fun deleteAllRemoteKeys()
}