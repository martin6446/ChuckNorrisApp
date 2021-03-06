package com.example.chucknorrisjokesapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChuckNorrisResponse(
    val categories: List<String>,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("icon_url")
    val iconUrl: String,
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val url: String,
    @SerialName("value")
    val joke : String
)