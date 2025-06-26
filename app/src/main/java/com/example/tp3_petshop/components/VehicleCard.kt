package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.viewmodel.VehicleViewModel

@Composable
fun VehicleCard(
    vehicle: Vehicle,
    onDetailClick: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: VehicleViewModel = hiltViewModel()

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
                IconButton(onClick = {showDialog = true} ) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Confirmar eliminación") },
                    text = { Text("¿Está seguro que desea eliminar el vehículo?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                            viewModel.deleteVehicle(vehicle.id)
                            viewModel.getAll()
                        }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}