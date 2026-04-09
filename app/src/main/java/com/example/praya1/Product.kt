package com.example.praya1

data class Product(
    val images: List<Int> = emptyList(),
    val name: String,
    val desc: String,
    val price: Int
)