package com.example.praya1.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praya1.components.BottomBar
import com.example.praya1.data.CartData
import com.example.praya1.components.CartRow
import com.example.praya1.data.ProductData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cart: List<CartData>, changeScreen: (Int) -> Unit, function: (ProductData) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Корзина") }, actions = {
            if (cart.isNotEmpty()) Button(onClick = {
                cart.forEach {
                    function(it.productData)
                }
            }) { Text("X") }
        })
    }, bottomBar = { BottomBar(changeScreen) }) {
        Surface(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            if (cart.isEmpty())
                Column(
                    modifier = Modifier.Companion.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Companion.CenterHorizontally
                ) {
                    Text("В вашей корзине пока пусто")
                    Text("Добавльте товары из каталога")
                    Button(onClick = { changeScreen(2) }) {
                        Text("Перейти к каталогу")
                    }
                } else
                Column(
                    modifier = Modifier.Companion.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    LazyColumn(Modifier.Companion.fillMaxWidth()) {
                        itemsIndexed(cart) { index, item ->
                            Card {
                                CartRow(item, index, function)
                            }
                        }
                    }
                    Card {
                        Row(
                            Modifier.Companion.fillMaxWidth().height(70.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            var summary by remember { mutableIntStateOf(0) }
                            Column(Modifier.Companion.fillMaxWidth()) {
                                Text("Вся сумма")
                                summary = 0
                                cart.forEach { summary += it.count.value * it.productData.price }
                                Text("$summary")

                            }
                            Button(
                                modifier = Modifier.Companion.fillMaxWidth(),
                                onClick = { cart.forEach { function(it.productData) } }) { Text("Оформить заказ") }

                        }
                    }
                }
        }
    }
}