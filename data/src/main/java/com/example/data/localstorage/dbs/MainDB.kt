package com.example.data.localstorage.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.datamodels.character.CharacterModel
import com.example.data.datamodels.location.LocationModel
import com.example.data.datamodels.remotekeys.CharacterRemoteKeys
import com.example.data.datamodels.remotekeys.LocationRemoteKeys
import com.example.data.localstorage.daos.CharacterDao
import com.example.data.localstorage.daos.CharacterRemoteKeysDao
import com.example.data.localstorage.daos.LocationDao
import com.example.data.localstorage.daos.LocationRemoteKeysDao

@Database(entities =
[CharacterModel::class,
    LocationModel::class,
LocationRemoteKeys::class,
CharacterRemoteKeys::class], version = 1)
abstract class MainDB:RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun locationDao(): LocationDao

    abstract fun characterRemoteKeysDao():CharacterRemoteKeysDao

    abstract fun locationRemoteKeysDao():LocationRemoteKeysDao
}