package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.PonyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PonyEndpoints {
    @GET("v1/character/all")
    fun getPonies(): Call<PonyResponse>

    @GET("v1/character/{name}")
    fun getPoniesByName(@Path("name") name: String?): Call<PonyResponse>
}