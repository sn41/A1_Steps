package com.example.praya1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(changeScreen: (Int) -> Unit) {
    BottomAppBar {
        Row(Modifier.Companion.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { changeScreen(2) }) { Text("Каталог") }
            TextButton(onClick = { changeScreen(4) }) { Text("Корзина") }
            TextButton(onClick = { changeScreen(5) }) { Text("Профиль") }
        }
    }
}