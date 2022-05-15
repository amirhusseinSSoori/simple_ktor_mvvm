package com.amirhusseinsoori.simple_ktor_mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.ApiService
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.model.UserResponseItem
import com.amirhusseinsoori.simple_ktor_mvvm.ui.theme.Simple_ktor_mvvmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Simple_ktor_mvvmTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val products = produceState(
                        initialValue = emptyList<UserResponseItem>(),
                        producer = {
                            value = apiService.getProducts()
                        }
                    )
                    Log.e("TAG", "onCreate: ${products.value}")
                }
            }
        }
    }
}

