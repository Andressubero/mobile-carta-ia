package com.example.tp3_petshop.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.VehicleStateCard

import com.example.tp3_petshop.viewmodel.VehicleStateViewModel


@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: VehicleStateViewModel = hiltViewModel()
) {
    val states by viewModel.vehicleStates.collectAsState()
    val error by viewModel.cartError.collectAsState()

    when {
        states.isEmpty() && error == null -> {
            // Estado inicial: cargando
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            // Error al cargar
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $error")
            }
        }
        else -> {
             // o .vehicleStates, si se llama así

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
                    items(states) { state ->
                        VehicleStateCard(
                            vehicle = state,
                            onDetailClick = { navController.navigate("detail/${state.id}") },
                            onReportClick = { navController.navigate("reportView/${state.id}") },
                            onChangeStatusClick = { navController.navigate("changeStatus/${state.id}") }
                        )
                    }
                }
            }
        }
    }
}
