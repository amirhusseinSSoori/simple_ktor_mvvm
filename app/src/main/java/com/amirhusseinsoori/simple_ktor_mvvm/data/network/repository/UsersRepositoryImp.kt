package com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository

import com.amirhusseinsoori.simple_ktor_mvvm.data.network.EndPoint.POST
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(private val client: HttpClient) : UsersRepository {
    override suspend fun getProducts(): Flow<Result<List<UserResponse>>> =
        flow<Result<List<UserResponse>>> {
            emit(Result.success(client.get { url(POST) }.body()))
        }.catch { ex ->
            emit(Result.failure(ex))
        }
}

