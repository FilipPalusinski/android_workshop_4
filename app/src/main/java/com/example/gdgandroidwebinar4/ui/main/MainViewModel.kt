package com.example.gdgandroidwebinar4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdgandroidwebinar4.data.PonyResponseCallback
import com.example.gdgandroidwebinar4.data.PonyService
import com.example.gdgandroidwebinar4.models.Pony
import com.example.gdgandroidwebinar4.models.PonyResponse
import com.google.gson.Gson
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpCookie
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {

    val ponyService = PonyService()

    val ponyList = MutableLiveData<List<Pony>>().apply {
        value = emptyList()
    }

    fun getPonyByUrl(){
        thread{
            val url = URL("https://pony-api.herokuapp.com/v1/character/all?limit=10")
            val connection = url.openConnection() as HttpURLConnection
            connection.apply{
                requestMethod = "GET"
            }

            val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
            var inputLine: String?
            val content = StringBuilder()

            while(inputStream.readLine().also{ inputLine = it }!= null){
                content.append(inputLine)
            }
            inputStream.close()

            val contentString = content.toString()

            val data = JSONTokener(contentString).nextValue() as JSONObject


            val gson = Gson()
            val ponyResponse = gson.fromJson(contentString, PonyResponse::class.java)

            ponyList.postValue(ponyResponse.data ?: listOf())
        }
    }

    fun getPonyWithRetrofit(){
        ponyService.getPonyList(object: PonyResponseCallback{
            override fun onSuccess(list: List<Pony>) {
                ponyList.value = list
            }

            override fun onError() {

            }

        })
    }
}
