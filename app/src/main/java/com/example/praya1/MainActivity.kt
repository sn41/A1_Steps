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
import com.example.praya1.screen.CartScreen
import com.example.praya1.data.CartData
import com.example.praya1.screen.DetailsScreen
import com.example.praya1.screen.CatalogScreen
import com.example.praya1.data.ProductData
import com.example.praya1.screen.ProfileScreen
import com.example.praya1.screen.RegistrationScreen
import com.example.praya1.screen.SingInScreen
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
                val productData by remember {
                    mutableStateOf(
                        listOf<ProductData>(
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
                    )
                }
                var cart by remember { mutableStateOf(listOf<CartData>()) }
                var arg by remember {
                    mutableStateOf(
                        ProductData(
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
                        RegistrationScreen(ChangeScreen(), SaveAccount())
                    }

                    1 -> {
                        SingInScreen(ChangeScreen(), SaveAccount())
                    }

                    2 -> {
                        CatalogScreen(ChangeScreen(), productData, { sel: ProductData -> arg = sel })
                    }

                    3 -> {
                        DetailsScreen(ChangeScreen(), arg, { productData: ProductData ->
                            cart += CartData(
                                productData, mutableIntStateOf(1)
                            )
                        }, cart)
                    }

                    4 -> {
                        CartScreen(
                            cart, ChangeScreen(), { rmProd: ProductData ->
                                cart = cart.filter { it.productData.name != rmProd.name }
                            })
                    }

                    5 -> {
                        ProfileScreen(account!!, ChangeScreen(), { account = null })
                    }

                }
            }
        }
    }
}


