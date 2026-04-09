package com.example.praya1.app

import androidx.compose.runtime.Composable
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens
import com.example.praya1.screen.CartScreen
import com.example.praya1.screen.CatalogScreen
import com.example.praya1.screen.DetailsScreen
import com.example.praya1.screen.ProfileScreen
import com.example.praya1.screen.RegistrationScreen
import com.example.praya1.screen.SingInScreen

@Composable
fun Navigation(
    model: MainViewModel
) {
    val screen = model.screen

    when (screen) {
        Screens.Registration -> RegistrationScreen(model)
        Screens.SingIn -> SingInScreen(model)
        Screens.Catalog -> CatalogScreen(model)
        Screens.Details -> DetailsScreen(model)
        Screens.Cart -> CartScreen(model)
        Screens.Profile -> ProfileScreen(model)
    }
}



