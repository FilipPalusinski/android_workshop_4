package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.PonyResponse
import retrofit2.Call
import retrofit2.http.GET

interface PonyEndpoints {

    @GET("v1/character/all")
    fun getPonyList(): Call<PonyResponse>


}