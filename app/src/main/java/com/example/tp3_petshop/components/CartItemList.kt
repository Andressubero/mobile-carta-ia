package com.example.tp3_petshop.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tp3_petshop.models.CartProductDetail

@Composable
fun CartItemList(
    products: List<CartProductDetail>,
    onDeleteClick: (CartProductDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            CartItem(product = product, onDeleteClick = { onDeleteClick(product) })
        }
    }
}
