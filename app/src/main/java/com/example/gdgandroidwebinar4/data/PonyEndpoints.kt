package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.PonyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PonyEndpoints {

    @GET("v1/character/all")
    suspend fun getPonyList(): PonyResponse

    @GET("v1/character/{name}")
    suspend fun getPonyListByName(@Path("name") name: String): PonyResponse

}