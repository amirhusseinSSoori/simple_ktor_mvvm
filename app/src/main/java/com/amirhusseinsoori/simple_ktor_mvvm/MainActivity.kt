package com.amirhusseinsoori.simple_ktor_mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.amirhusseinsoori.simple_ktor_mvvm.ui.theme.Simple_ktor_mvvmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
               val viewModel:MainViewModel by viewModels()
            Simple_ktor_mvvmTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    viewModel.state.collectAsState().let {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(it.value.list) { 
                            Text(text = it.title)
                        }
                    }

                    }

                }
            }
        }
    }
}

