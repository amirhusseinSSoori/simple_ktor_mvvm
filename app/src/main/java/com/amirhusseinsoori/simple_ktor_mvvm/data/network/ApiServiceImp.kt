package com.amirhusseinsoori.simple_ktor_mvvm.data.network

import com.amirhusseinsoori.simple_ktor_mvvm.data.network.EndPoint.BASE_URL
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.EndPoint.POST
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.text.get

class ApiServiceImp constructor( private val client: HttpClient):ApiService{
    override suspend fun getProducts(): List<UserResponseItem> {
        return try {
            client.get { url(POST) }.body()
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            emptyList()
        }
    }

}