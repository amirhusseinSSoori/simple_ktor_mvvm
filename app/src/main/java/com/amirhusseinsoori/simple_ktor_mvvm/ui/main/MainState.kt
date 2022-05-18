package com.amirhusseinsoori.simple_ktor_mvvm.ui.main

import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse

data class MainState(
    val list: List<UserResponse> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)