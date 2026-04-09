package com.example.praya1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.praya1.ui.theme.PrayA1Theme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayA1Theme {
                var screen by remember { mutableIntStateOf(1) }
                var account by remember { mutableStateOf<Account?>(null) }
                val products by remember {
                    mutableStateOf(
                        listOf<Product>(
                            Product(
                                listOf(R.drawable.ic_launcher_background),
                                name = "1",
                                desc = "",
                                1000
                            ), Product(
                                listOf(R.drawable.ic_launcher_background),
                                name = "2",
                                desc = "",
                                1000
                            ), Product(
                                listOf(R.drawable.ic_launcher_background),
                                name = "3",
                                desc = "",
                                1000
                            )
                        )
                    )
                }
                var cart by remember { mutableStateOf(listOf<CartItem>()) }
                var arg by remember {
                    mutableStateOf(
                        Product(
                            listOf(R.drawable.ic_launcher_background),
                            name = "3",
                            desc = "",
                            1000
                        )
                    )
                }

                fun ChangeScreen(): (Int) -> Unit = { screenId: Int -> screen = screenId }
                fun SaveAccount(): (Account) -> Unit = { newAccount -> account = newAccount }
                when (screen) {
                    0 -> {
                        Registration(ChangeScreen(), SaveAccount())
                    }

                    1 -> {
                        SingIn(ChangeScreen(), SaveAccount())
                    }

                    2 -> {
                        Catalog(ChangeScreen(), products, { sel: Product -> arg = sel })
                    }

                    3 -> {
                        Details(ChangeScreen(), arg, { product: Product ->
                            cart += CartItem(
                                product, mutableIntStateOf(1)
                            )
                        },cart)
                    }

                    4 -> {
                        Cart(
                            cart, ChangeScreen(), { rmProd: Product ->
                                cart = cart.filter { it.product.name != rmProd.name }
                            })
                    }

                    5 -> {
                        Profile(account!!, ChangeScreen(), { account = null })
                    }

                }
            }
        }
    }
}


