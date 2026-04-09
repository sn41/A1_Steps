package com.example.praya1.data

import androidx.compose.runtime.MutableState

data class CartData(
    val productData: ProductData, val count: MutableState<Int>
)