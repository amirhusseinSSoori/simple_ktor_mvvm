package com.amirhusseinsoori.simple_ktor_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.amirhusseinsoori.simple_ktor_mvvm.ui.main.MainViewModel
import com.amirhusseinsoori.simple_ktor_mvvm.ui.theme.Simple_ktor_mvvmTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
               val viewModel = getViewModel<MainViewModel>()
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

