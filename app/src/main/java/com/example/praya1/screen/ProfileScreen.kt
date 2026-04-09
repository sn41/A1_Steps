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
import com.example.praya1.Account
import com.example.praya1.R
import com.example.praya1.components.BottomBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(account: Account, changeScreen: (Int) -> Unit, function: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Профиль") }) },
        bottomBar = { BottomBar(changeScreen) }) {
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
                Text(account.name)
                Text(account.email)
                TextButton(onClick = {
                    function()
                    changeScreen(1)
                }) { Text("Выйти") }
            }
        }

    }
}