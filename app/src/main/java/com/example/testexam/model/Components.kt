package com.example.testexam.model

import io.github.jan.supabase.postgrest.query.Count
import kotlinx.serialization.Serializable
import okhttp3.internal.ws.RealWebSocket

@Serializable
data class Components (
    val id: Int =0,
    val name: String ="",
    val description: String = "",
    val image_url:String ="",
    val category: Int =0,
    val cost: Int =0,
    val count: Int =0
)