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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.models.VehicleState
@Composable
fun VehicleStateCard(
    vehicle: VehicleState,
    onDetailClick: () -> Unit,
    onReportClick: () -> Unit,
    onChangeStatusClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Marca: ${vehicle.vehicle_brand}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Modelo: ${vehicle.vehicle_model}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Text(
                    text = "Creado: ${vehicle.creation_date?.substring(0, 10)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Text(
                    text = "Estado: ${vehicle.validation_state}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (vehicle.validation_state == "APROBADA") Color(0xFF2E7D32) else Color(0xFFD32F2F)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onDetailClick, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Info, contentDescription = "Ver detalle", modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = onReportClick, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Build, contentDescription = "Ver reporte", modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = onChangeStatusClick, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Cambiar estado", modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

