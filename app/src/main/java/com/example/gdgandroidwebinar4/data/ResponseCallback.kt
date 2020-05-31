package com.example.gdgandroidwebinar4.data

interface ResponseCallback<T> {
    fun onSuccess(result: T)
    fun onError()
}