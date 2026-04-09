package com.example.praya1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
 fun CartRow(
    item: CartItem, index: Int, function: (Product) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Image(
            painter = painterResource(item.product.images[0]), contentDescription = null
        )
        Column() {
            Text("товар${index}")
            Text(item.product.price.toString())
        }
        Row(verticalAlignment = Alignment.Companion.CenterVertically) {
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