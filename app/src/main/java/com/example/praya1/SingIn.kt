package com.example.praya1

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingIn(
    changeScreen: (Int) -> Unit, saveAccount: (Account) -> Unit
) {
    Scaffold(

        topBar = { TopAppBar(title = { Text("Авторизация") }) }) {

        var emailState = rememberTextFieldState()
        var loginState = rememberTextFieldState()
        var password by remember { mutableStateOf("") }
        var isHidden by remember { mutableStateOf(true) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier.Companion.fillMaxSize().padding(it)
        ) {
            OutlinedTextField(state = emailState, label = { Text("Почта") }, supportingText = {
                if (emailState.text.toString().isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) Text("Введите корректный адрес")
            })
            OutlinedTextField(
                value = password,
                onValueChange = { sit: String -> password = sit },
                label = { Text("Пароль") },
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.Companion.None,
                supportingText = { if (!password.isNotBlank()) Text("Заполните все поля ") })

            Button(onClick = {
                if (
                    password.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) {
                    saveAccount(
                        Account(
                            name = loginState.text.toString(),
                            email = emailState.text.toString(),
                            password = password
                        )
                    )
                    changeScreen(2)
                }
            }) {
                Text("Войти")
            }
            Button(onClick = { changeScreen(0) }) {
                Text("зарегистрироваться")
            }
        }
    }
}