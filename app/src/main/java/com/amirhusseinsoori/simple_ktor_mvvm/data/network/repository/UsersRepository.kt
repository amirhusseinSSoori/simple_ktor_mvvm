package com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository

import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getProducts(): Flow<Result<List<UserResponse>>>
}