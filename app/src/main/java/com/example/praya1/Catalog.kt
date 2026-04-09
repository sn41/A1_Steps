package com.example.praya1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalog(
    changeScreen: (Int) -> Unit, products: List<Product>, function2: (Product) -> Unit
) {
    var searchState = rememberTextFieldState()
    Scaffold(topBar = {
        TopAppBar(title = {
            OutlinedTextField(
                state = searchState,
                leadingIcon = { Text("Q") },
                modifier = Modifier.Companion.fillMaxWidth()
            )
        })
    }, bottomBar = {
        BottomBar(changeScreen)
    }

    ) { it ->
        LazyVerticalGrid(
            GridCells.Fixed(2), modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            items(products.filter {
                it.name.contains(
                    searchState.text.toString(), ignoreCase = true
                )
            }) {
                val pagestate = rememberPagerState { it.images.size }
                Card(modifier = Modifier.Companion.padding(20.dp)) {
                    Column(
                        Modifier.Companion
                            .fillMaxSize()
                            .clickable(onClick = {
                                function2(it)
                                changeScreen(3)
                            })
                    ) {
                        HorizontalPager(
                            state = pagestate
                        ) { page ->
                            Image(
                                painter = painterResource(it.images[page]),
                                contentDescription = null,
                                modifier = Modifier.Companion.height(200.dp)
                            )
                        }
                        Text(it.name)
                        Text(it.desc)
                    }
                }
            }
        }
    }
}