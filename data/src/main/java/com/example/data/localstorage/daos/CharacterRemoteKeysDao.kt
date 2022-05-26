package com.example.data.localstorage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodels.remotekeys.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {

    @Query("SELECT * FROM CharacterRemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): CharacterRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM CharacterRemoteKeys")
    suspend fun deleteAllRemoteKeys()
}