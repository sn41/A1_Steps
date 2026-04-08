package com.example.praya1

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.praya1.ui.theme.PrayA1Theme

/*
object FileSystem{

}*/
data class Account(
    val name: String, val email: String, val password: String
)

data class Product(
    val images: List<Int> = emptyList(),
    val name: String,
    val desc: String,
    val price: Int
)

data class CartItem(
    val product: Product, val count: MutableState<Int>
)


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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Details(
    changeScreen: (Int) -> Unit, arg: Product, function2: (Product) -> Unit, cart: List<CartItem>
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Button(onClick = { changeScreen(2) }) {
                Text("<")
            }
        })
    }, bottomBar = {
        BottomAppBar {
            val isIn = cart.find { it.product.name==arg.name }!=null
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if(!isIn){function2(arg)}
                changeScreen(2)
            }) {
                if (isIn) Text("Уже в корзине") else {
                    Text("Добавить в корзину")

                }
            }
        }
    }) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val pagestate = rememberPagerState { arg.images.size }
            HorizontalPager(
                pagestate

            ) { page ->
                Image(
                    painter = painterResource(arg.images[page]),
                    contentDescription = null,
                    modifier = Modifier.height(200.dp)
                )
            }
            Text(arg.name)
            Text(arg.price.toString())
            Text(arg.desc)
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Profile(account: Account, changeScreen: (Int) -> Unit, function: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Профиль") }) },
        bottomBar = { BottomBar(changeScreen) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(account.name)
                Text(account.email)
                TextButton(onClick = {
                    function()
                    changeScreen(1)
                }) { Text("Выйти") }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(cart: List<CartItem>, changeScreen: (Int) -> Unit, function: (Product) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Корзина") }, actions = {
            if (cart.isNotEmpty()) Button(onClick = {
                cart.forEach {
                    function(it.product)
                }
            }) { Text("X") }
        })
    }, bottomBar = { BottomBar(changeScreen) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (cart.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("В вашей корзине пока пусто")
                    Text("Добавльте товары из каталога")
                    Button(onClick = { changeScreen(2) }) {
                        Text("Перейти к каталогу")
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
                    LazyColumn(Modifier.fillMaxWidth()) {
                        itemsIndexed(cart) { index, item ->
                            Card {
                                CartRow(item, index, function)
                            }
                        }
                    }
                    Card{
                        Row(
                            Modifier.fillMaxWidth().height(70.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            var summary by remember { mutableIntStateOf(0) }
                            Column(Modifier.fillMaxWidth()) {
                                Text("Вся сумма")
                                summary = 0
                                cart.forEach { summary += it.count.value * it.product.price }
                                Text("$summary")

                            }
                            Button(modifier = Modifier.fillMaxWidth() , onClick = { cart.forEach { function(it.product) } }) { Text("Оформить заказ") }

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CartRow(
    item: CartItem, index: Int, function: (Product) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly , modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)){
        Image(
            painter = painterResource(item.product.images[0]), contentDescription = null
        )
        Column() {
            Text("товар${index}")
            Text(item.product.price.toString())
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                if (item.count.value == 1) {
                    function(item.product)
                } else {
                    item.count.value--
                }
            }) { Text("-") }
            Text("${item.count.value}шт")
            Button(onClick = { item.count.value++ }) { Text("+") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalog(
    changeScreen: (Int) -> Unit, products: List<Product>, function2: (Product) -> Unit
) {
    var searchState = rememberTextFieldState()
    Scaffold(topBar = {
        TopAppBar(title = {
            OutlinedTextField(
                state = searchState, leadingIcon = { Text("Q") }, modifier = Modifier.fillMaxWidth())
        })
    }, bottomBar = {
        BottomBar(changeScreen)
    }

    ) { it ->
        LazyVerticalGrid(
            GridCells.Fixed(2), modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(products.filter {
                it.name.contains(
                    searchState.text.toString(), ignoreCase = true
                )
            }) {
                val pagestate = rememberPagerState { it.images.size }
                Card(modifier = Modifier.padding(20.dp)) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .clickable(onClick = {
                                function2(it)
                                changeScreen(3)
                            })
                    ) {
                        HorizontalPager(
                            state = pagestate
                        ) { page ->
                            Image(
                                painter = painterResource(it.images[page]),
                                contentDescription = null,
                                modifier = Modifier.height(200.dp)
                            )
                        }
                        Text(it.name)
                        Text(it.desc)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(changeScreen: (Int) -> Unit) {
    BottomAppBar {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { changeScreen(2) }) { Text("Каталог") }
            TextButton(onClick = { changeScreen(4) }) { Text("Корзина") }
            TextButton(onClick = { changeScreen(5) }) { Text("Профиль") }
        }
    }
}

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
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(it)) {
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
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.None,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(
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
        Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(it)) {
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
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.None,
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

