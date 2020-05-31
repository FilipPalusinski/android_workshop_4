package com.example.gdgandroidwebinar4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdgandroidwebinar4.data.PonyService
import com.example.gdgandroidwebinar4.data.ResponseCallback
import com.example.gdgandroidwebinar4.models.Pony
import com.example.gdgandroidwebinar4.models.PonyResponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {

    private val service: PonyService by lazy {
        PonyService()
    }

    val ponyList = MutableLiveData<List<Pony>>().apply {
        value = emptyList()
    }

    val searchInProgress = MutableLiveData(false)

    fun getPonyUrlExample() {
        thread {
            val url = URL("https://pony-api.herokuapp.com/v1/character/all?limit=10")
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.apply {
                requestMethod = "GET"
            }

            val inStream = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            val content = StringBuffer()
            while (inStream.readLine().also { inputLine = it } != null) {
                content.append(inputLine)
            }
            inStream.close()

            val contentString = content.toString()

            // val data = (JSONTokener(contentString).nextValue() as JSONObject).get("data")

            val gson = Gson()
            val ponyResponse = gson.fromJson(contentString, PonyResponse::class.java)
            ponyList.postValue(ponyResponse.data)
        }
    }

    fun searchPony(name: String?) {
        searchInProgress.value = true

        name.takeUnless {
            it.isNullOrEmpty()
        }?.let {
            service.getPonyByName(it, ponyResponseCallback)
        }
            ?: run {
                service.getAllPonies(ponyResponseCallback)
            }
    }

    private val ponyResponseCallback = object : ResponseCallback<List<Pony>> {
        override fun onSuccess(result: List<Pony>) {
            searchInProgress.value = false
            ponyList.value = result
        }

        override fun onError() {
            searchInProgress.value = false
        }
    }
}
