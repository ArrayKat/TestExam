package com.example.testexam.model

import kotlinx.serialization.Serializable

@Serializable
data class MakerComponents (
    val id:Int = 0,
    val id_component: Int =0 ,
    val id_maker: Int = 0
)