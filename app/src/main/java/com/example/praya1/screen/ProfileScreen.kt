package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.praya1.R
import com.example.praya1.components.BottomBar
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(model: MainViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Профиль") }) },
        bottomBar = { BottomBar({ model.navigateTo(it)}) }) {
        Surface(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            Column(horizontalAlignment = Alignment.Companion.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.Companion.fillMaxWidth()
                )
                Text(model.account?.name ?: "")
                Text(model.account?.email ?: "")
                TextButton(onClick = {
                    model.resetAccount()
                    model.navigateTo(Screens.SingIn)
                }) { Text("Выйти") }
            }
        }

    }
}