package com.amirhusseinsoori.simple_ktor_mvvm.data.di


import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepository
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepositoryImp
import com.amirhusseinsoori.simple_ktor_mvvm.ui.main.MainViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { provideClientService() }

    single<UsersRepository> { UsersRepositoryImp(get()) }

    viewModel { MainViewModel(get()) }


}

fun provideClientService(): HttpClient {
    return HttpClient(Android) {
        // Logging
        install(Logging) {
            level = LogLevel.ALL
        }
        // JSON
        install(ContentNegotiation) {
            json()
        }
        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }
        // Apply to all requests
        defaultRequest {
            // Parameter("api_key", "some_api_key")
            // Content Type
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}



