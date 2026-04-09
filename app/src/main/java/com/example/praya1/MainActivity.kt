package com.example.praya1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.praya1.app.Navigation
import com.example.praya1.models.MainViewModel
import com.example.praya1.ui.theme.PrayA1Theme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val model: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayA1Theme {
                Navigation(model)
            }
        }
    }
}


