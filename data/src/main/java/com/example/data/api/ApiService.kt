package com.example.data.api

import com.example.data.datamodels.character.CharacterResponseModel
import com.example.data.datamodels.location.LocationResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val BASE_URL= "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getPagedCharacter(
        @Query("page") page:Int
    ):Response<CharacterResponseModel>

    @GET("location")
    suspend fun getPagedLocation(
        @Query("page") page:Int
    ):Response<LocationResponseModel>

    @GET("character")
    suspend fun getPagedCharacterParticular(
        @Query("page") page:Int,
        @Query("name") chName:String
    ):Response<CharacterResponseModel>

    @GET("location")
    suspend fun getPagedLocationParticular(
        @Query("page") page:Int,
        @Query("name") locName:String
    ):Response<LocationResponseModel>
}