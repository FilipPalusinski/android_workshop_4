package com.example.gdgandroidwebinar4.models

data class Pony(
    val id: Int,
    val name: String,
    val alias: String?,
    val sex: String?,
    val occupation: String?,
    val image: List<String>?
)