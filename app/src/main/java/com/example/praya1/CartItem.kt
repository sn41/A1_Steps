package com.example.praya1

import androidx.compose.runtime.MutableState

data class CartItem(
    val product: Product, val count: MutableState<Int>
)