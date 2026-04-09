package com.example.praya1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.praya1.models.Screens

@Composable
fun BottomBar(onNavigate: (Screens) -> Unit) {
    BottomAppBar {
        Row(Modifier.Companion.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { onNavigate(Screens.Catalog) }) { Text("Каталог") }
            TextButton(onClick = { onNavigate(Screens.Cart) }) { Text("Корзина") }
            TextButton(onClick = { onNavigate(Screens.Profile)}) { Text("Профиль") }
        }
    }
}