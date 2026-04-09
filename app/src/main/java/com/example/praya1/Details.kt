package com.example.praya1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun Details(
    changeScreen: (Int) -> Unit, arg: Product, function2: (Product) -> Unit, cart: List<CartItem>
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Button(onClick = { changeScreen(2) }) {
                Text("<")
            }
        })
    }, bottomBar = {
        BottomAppBar {
            val isIn = cart.find { it.product.name == arg.name } != null
            Button(modifier = Modifier.Companion.fillMaxWidth(), onClick = {
                if (!isIn) {
                    function2(arg)
                }
                changeScreen(2)
            }) {
                if (isIn) Text("Уже в корзине") else {
                    Text("Добавить в корзину")

                }
            }
        }
    }) { it ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            val pagestate = rememberPagerState { arg.images.size }
            HorizontalPager(
                pagestate

            ) { page ->
                Image(
                    painter = painterResource(arg.images[page]),
                    contentDescription = null,
                    modifier = Modifier.Companion.height(200.dp)
                )
            }
            Text(arg.name)
            Text(arg.price.toString())
            Text(arg.desc)
        }
    }
}