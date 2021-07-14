package com.example.gdgandroidwebinar4.data

import com.example.gdgandroidwebinar4.models.Pony

interface PonyResponseCallback {
    fun onSuccess(list: List<Pony>)
    fun onError()
}