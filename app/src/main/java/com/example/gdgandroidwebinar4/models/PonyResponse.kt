package com.example.gdgandroidwebinar4.models

data class PonyResponse(
    val status: Int,
    val data: List<Pony>?,
    val error: String?

)