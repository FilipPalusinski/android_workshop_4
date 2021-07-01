package com.example.gdgandroidwebinar4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val ponyList = MutableLiveData<List<Pony>>().apply {
        value = listOf(
            Pony(1, "Big McIntosh", "Big Mac", "male", "Farmer\\Pony Tones bass singer", listOf()),
            Pony(2, "Fluttershy", "", "female", "Animal caretaker", listOf()),
            Pony(3, "Twilight Sparkle", "Princess Twilight Sparkle", "female", "Ruler of Equestria (S9E26)", listOf())
        )
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


}
