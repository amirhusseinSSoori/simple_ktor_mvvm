package com.amirhusseinsoori.simple_ktor_mvvm.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

suspend fun <T> Flow<Result<T>>.handleEvent(
    loading: (isLoading: Boolean) -> Unit,
    success: (T) -> Unit,
    failed: (message: String) -> Unit
) {
    return onStart {
        loading(true)
    }.onCompletion {
        loading(false)
    }.collect() { data ->
        data.fold(onSuccess = { success ->
            success(success)
        }, onFailure = {
            failed(it.message ?: "")
        })
    }
}
