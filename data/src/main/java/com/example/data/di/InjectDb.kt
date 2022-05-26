package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.localstorage.dbs.MainDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InjectDb {

    @Provides
    @Singleton
    fun providesDb(
        @ApplicationContext context: Context
    ):MainDB = Room.databaseBuilder(context, MainDB::class.java, "MainDataBase").build()
}