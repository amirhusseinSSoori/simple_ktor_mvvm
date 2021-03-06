package com.amirhusseinsoori.simple_ktor_mvvm.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)