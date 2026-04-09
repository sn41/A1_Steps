package com.example.praya1.data

data class ProductData(
    val images: List<Int> = emptyList(),
    val name: String,
    val desc: String,
    val price: Int
)