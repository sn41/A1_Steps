package com.example.praya1.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praya1.data.Account
import com.example.praya1.R
import com.example.praya1.data.CartData
import com.example.praya1.data.ProductData

enum class Screens {
    Registration, SingIn, Catalog, Details, Cart, Profile
}

class MainViewModel : ViewModel() {
    var cart = listOf<CartData>()
    val productData = listOf<ProductData>(

        ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "1",
            desc = "",
            1000
        ), ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "2",
            desc = "",
            1000
        ), ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "3",
            desc = "",
            1000
        )
    )
    var selectedProduct: ProductData? by mutableStateOf(null)

    fun cartNotContainItem(): Boolean{
        return cart.find { it.productData.name == selectedProduct?.name } != null
    }

    var screen by mutableStateOf(Screens.Registration)

    var account by mutableStateOf<Account?>(null)
    fun resetAccount() { account = null }
    fun addToCart(): (ProductData) -> Unit = { productData: ProductData ->
        cart + CartData(
            productData, mutableIntStateOf(1)
        )
    }

    fun deleteCartItem(): (ProductData) -> Unit = { rmProd: ProductData ->
        cart = cart.filter { it.productData.name != rmProd.name }
    }

    fun selectnew(sel: ProductData) {selectedProduct = sel }


    fun navigateTo(screen: Screens) {
        this.screen = screen
    }

    fun saveAccount(newAccount : Account) { account = newAccount }


}