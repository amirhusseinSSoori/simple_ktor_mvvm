package com.amirhusseinsoori.simple_ktor_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepository
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

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
                    mutableState.value = mutableState.value.copy(message = it.message?:"")
                })
            }

        }

    }
}


data class MainState(val list: List<UserResponse> = emptyList(), val message: String = "")