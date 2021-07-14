package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.Pony
import com.example.gdgandroidwebinar4.models.PonyResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

class PonyService {
    private val endpoints: PonyEndpoints


    init{

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pony-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        endpoints = retrofit.create(PonyEndpoints::class.java)
    }


   suspend fun getPonyList(): List<Pony>{
        val result = endpoints.getPonyList()

       if(result.status == 200){
           return result.data ?: listOf()
       }else{
           throw IllegalStateException()
       }
    }

    suspend fun getPonyListByName(name: String): List<Pony>{
        val result = endpoints.getPonyListByName(name)

        if(result.status == 200){
            return result.data ?: listOf()
        }else{
            throw IllegalStateException()
        }
    }



}