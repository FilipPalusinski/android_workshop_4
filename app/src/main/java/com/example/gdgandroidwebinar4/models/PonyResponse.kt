package com.example.gdgandroidwebinar4.models

data class PonyResponse(
    val status: String,
    val data: List<Pony>?,
    val error: String?

)