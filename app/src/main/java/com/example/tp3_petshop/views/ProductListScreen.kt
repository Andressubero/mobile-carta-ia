package com.example.tp3_petshop.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ProductCard
import com.example.tp3_petshop.viewmodel.FavoriteProductViewModel
import com.example.tp3_petshop.viewmodel.ProductUiState
import com.example.tp3_petshop.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ProductUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProductUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error al cargar los productos")
            }
        }
        is ProductUiState.Success -> {
            val products = (uiState as ProductUiState.Success).products

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .clickable { navController.navigate("bestSellerView") }
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Best Seller",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(products) { product ->
                        ProductCard(product = product) {
                            navController.navigate("detail/${product.id}")
                        }
                    }
                }
            }
        }
    }
}

