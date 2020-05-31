package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.Pony
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PonyService {
    private val endpoints: PonyEndpoints

    init {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pony-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        endpoints = retrofit.create(PonyEndpoints::class.java)
    }

    suspend fun getAllPonies(): List<Pony> {
        val response = endpoints.getPonies()
        return when (response.status) {
            200 -> response.data ?: throw Exception(response.error)
            else -> throw Exception(response.error)
        }
    }

    suspend fun getPonyByName(name: String): List<Pony> {
        val response = endpoints.getPoniesByName(name)
        return when (response.status) {
            200 -> response.data ?: throw Exception(response.error)
            else -> throw Exception(response.error)
        }
    }
}