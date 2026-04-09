package com.example.praya1.screen

import android.util.Patterns
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
import com.example.praya1.Account

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    changeScreen: (Int) -> Unit, saveAccount: (Account) -> Unit
) {
    var emailState = rememberTextFieldState()
    var loginState = rememberTextFieldState()
    var password by remember { mutableStateOf("") }
    var isHidden by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("регистрация") },
                actions = { Button(onClick = { changeScreen(1) }) { Text("<") } })
        }) {
        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier.Companion.fillMaxSize().padding(it)
        ) {
            OutlinedTextField(state = emailState, label = { Text("Почта") }, supportingText = {
                if (emailState.text.toString().isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) Text("Введите корректный адрес")
            })
            OutlinedTextField(state = loginState, label = { Text("Логин") }, supportingText = {
                if (!loginState.text.toString().isNotBlank()) Text("Заполните все поля ")
            })

            OutlinedTextField(
                value = password,
                onValueChange = { sit: String -> password = sit },
                label = { Text("Пароль") },
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.Companion.None,
                trailingIcon = { Button(onClick = { isHidden = !isHidden }) { Text("*") } },
                supportingText = { if (!password.isNotBlank()) Text("Заполните все поля ") })

            Button(onClick = {
                if (loginState.text.toString()
                        .isNotBlank() && password.isNotBlank() && emailState.text.toString()
                        .isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString())
                        .matches()
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


            }) { Text("Создать аккаунт") }

        }
    }
}