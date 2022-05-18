package com.amirhusseinsoori.simple_ktor_mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepository
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse
import com.amirhusseinsoori.simple_ktor_mvvm.ui.handleEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel constructor(private val repository: UsersRepository) : ViewModel() {

    private val mutableState = MutableStateFlow(MainState())
    val state = mutableState.asStateFlow()

    init {
        event()
    }


    private fun event() {
        viewModelScope.launch {
            repository.getProducts().handleEvent(loading = { loading ->
                mutableState.value = mutableState.value.copy(isLoading = loading)
            }, success = { value ->
                mutableState.value = mutableState.value.copy(list = value)
            }, failed = { message ->
                mutableState.value = mutableState.value.copy(message = message ?: "")
            })
        }

    }
}
