package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.Vehicle
@Composable
fun VehicleCard(
    vehicle: Vehicle,
    onDetailClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Marca: ${vehicle.brand}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Modelo: ${vehicle.model}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Año: ${vehicle.year}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Patente: ${vehicle.plate}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onDetailClick) {
                    Icon(Icons.Default.Info, contentDescription = "Ver detalle")
                }
            }
        }
    }
}