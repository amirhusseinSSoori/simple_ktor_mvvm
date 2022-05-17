package com.amirhusseinsoori.simple_ktor_mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepository
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel constructor(private val repository: UsersRepository) : ViewModel() {

    private val mutableState = MutableStateFlow(MainState())
    val state = mutableState.asStateFlow()

    init {
        event()
    }


    private fun event() {
        viewModelScope.launch {
            repository.getProducts().collect() { data ->
                data.fold(onSuccess = { list ->
                    mutableState.value = mutableState.value.copy(list = list)
                }, onFailure = {
                    mutableState.value = mutableState.value.copy(message = it.message ?: "")
                })
            }

        }

    }
}


data class MainState(val list: List<UserResponse> = emptyList(), val message: String = "")