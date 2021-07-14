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


    fun getPonyList(callback: PonyResponseCallback){
        endpoints.getPonyList().enqueue(object : Callback<PonyResponse> {
            override fun onResponse(call: Call<PonyResponse>, response: Response<PonyResponse>) {
                val body = response.body()
                if(body?.data != null){
                    callback.onSuccess(body.data)
                }else{
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<PonyResponse>, t: Throwable) {
                callback.onError()
            }


        })
    }
}