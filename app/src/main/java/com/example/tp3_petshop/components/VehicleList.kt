package com.example.tp3_petshop.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp3_petshop.models.Vehicle


@Composable
fun VehicleList(
    navController: NavController,
    vehicles: List<Vehicle>,
) {
        if (vehicles.isEmpty()) {
            Text("Aún no tienes vehículos creados")
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Box(
                modifier = Modifier
                    .clickable { navController.navigate("bestSellerView") }
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Vehículos Registrados",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxHeight().weight(1f)
            ) {
                items(vehicles) { state ->
                    VehicleCard(
                        vehicle = state,
                        onDetailClick = { navController.navigate("vehicleStateForm/${state.id}") },
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate("createVehicle")
                },
                enabled = true
            ) {
                Text("Crear un Vehículo")
            }
        }
}