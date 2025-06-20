package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.VehicleState

@Composable
fun VehicleStateCard(vehicle: VehicleState, onDetailClick: () -> Unit, onReportClick: () -> Unit, onChangeStatusClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(220.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Marca: ${vehicle.vehicle_brand}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Modelo: ${vehicle.vehicle_model}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Creado: ${vehicle.creation_date.substring(0, 10)}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Estado: ${vehicle.validation_state}",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (vehicle.validation_state == "APROBADA") Color(0xFF2E7D32) else Color(0xFFD32F2F)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = onDetailClick) {
                    Icon(Icons.Default.Info, contentDescription = "Ver detalle")
                }
                IconButton(onClick = onReportClick) {
                    Icon(Icons.Default.Build, contentDescription = "Ver reporte")
                }
                IconButton(onClick = onChangeStatusClick) {
                    Icon(Icons.Default.Add, contentDescription = "Cambiar estado")
                }
            }
        }
    }
}
