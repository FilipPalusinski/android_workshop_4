package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.Pony
import com.example.gdgandroidwebinar4.models.PonyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getAllPonies(callback: ResponseCallback<List<Pony>>) {
        endpoints.getPonies().enqueue(PonyCallback(callback))
    }

    fun getPonyByName(name: String, callback: ResponseCallback<List<Pony>>) {
        endpoints.getPoniesByName(name).enqueue(PonyCallback(callback))
    }

    private class PonyCallback(
        private val callback: ResponseCallback<List<Pony>>
    ) : Callback<PonyResponse> {

        override fun onFailure(call: Call<PonyResponse>, t: Throwable) {
            callback.onError()
        }

        override fun onResponse(call: Call<PonyResponse>, response: Response<PonyResponse>) {
            val body = response.body()

            when (body?.status) {
                200 -> body.data?.let { callback.onSuccess(it) } ?: callback.onError()
                else -> callback.onError()
            }
        }
    }
}