package com.example.testexam.model

import kotlinx.serialization.Serializable

@Serializable
data class Category (
    val id:Int= 0,
    val name:String =""
)