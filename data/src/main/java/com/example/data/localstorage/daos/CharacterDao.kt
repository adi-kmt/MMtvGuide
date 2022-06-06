package com.example.data.localstorage.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodels.character.CharacterModel

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLCharacters(characterList:List<CharacterModel>)

    @Query("SELECT * FROM Character WHERE name LIKE :characterName")
    fun getPagedCharacters(characterName:String?=null):PagingSource<Int, CharacterModel>

    @Query("DELETE FROM Character")
    fun deleteAllCharacters()

}