package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.CartItemList
import com.example.tp3_petshop.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(navController: NavController,
             cartViewModel: CartViewModel = hiltViewModel()) {
    val cart by cartViewModel.cart.collectAsState()
    val isCartEmpty = cart?.products.isNullOrEmpty()

    fun onCheckout() {
        navController.navigate("payment")
    }

    LaunchedEffect(Unit) {
        cartViewModel.getCart()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Cart", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (!isCartEmpty) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${cart?.products?.sumOf { it.quantity } ?: 0} Items", color = Color.Gray)
                        Text("$${"%.2f".format(cart?.products?.sumOf { it.price * it.quantity } ?: 0.0)}", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Tax", color = Color.Gray)
                        Text("$1.99", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Totals", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("$${"%.2f".format(cart?.products?.sumOf { it.price * it.quantity }?.plus(1.99) ?: 0.0)}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onCheckout() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF735BF2)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("Checkout", fontSize = 18.sp)
                    }
                }
            }
        }
    ) { innerPadding ->
        when {
            cart == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            isCartEmpty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío.")
                }
            }

            else -> {
                CartItemList(
                    products = cart?.products ?: emptyList(),
                    onDeleteClick = { },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

}

