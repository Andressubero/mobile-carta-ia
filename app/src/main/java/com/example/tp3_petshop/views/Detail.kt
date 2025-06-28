package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.TopBarSection
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel


@Composable
fun DetailView(
    stateId: String,
    navController: NavController,
    viewModel: VehicleStateViewModel,
) {
    val stateDetail by viewModel.stateDetail.collectAsState()

    LaunchedEffect(stateId) {
        if (!stateId.isEmpty()) {
            viewModel.getById(stateId);
        }
    }



    stateDetail?.let {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    TopBarSection(
                        title = "Información del vehículo",
                        showFavorite = false,
                        isFavorite = false,
                        onBackClick = { navController.popBackStack() },
                        onFavoriteClick = {}
                    )

                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .padding(top = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Datos generales
//                        Text("ID: ${vehicle.id}", style = MaterialTheme.typography.bodyMedium)
                        Text("Marca: ${stateDetail!!.vehicle_brand}", style = MaterialTheme.typography.bodyMedium)
                        Text("Modelo: ${stateDetail!!.vehicle_model}", style = MaterialTheme.typography.bodyMedium)
                        Text("Fecha de creación: ${stateDetail!!.creation_date?.substring(0, 10)}", style = MaterialTheme.typography.bodyMedium)
                        Text("Fecha de declaración: ${stateDetail!!.declared_date}", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "Estado: ${stateDetail!!.validation_state}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (stateDetail!!.validation_state == "APROBADA") Color(0xFF2E7D32) else Color(0xFFD32F2F),
                            fontWeight = FontWeight.Bold
                        )

                        Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                        // Validación
                        Text("Razones de validación:", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Text(stateDetail!!.validation_reasons.toString(), style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(8.dp))

                        // Partes del vehículo
                        Text("Partes del vehículo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        stateDetail!!.parts_state?.forEach { part ->
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Divider(thickness = 0.8.dp, color = Color.Gray)
                                Text("• ${part.vehicle_part_name}", fontWeight = FontWeight.SemiBold)
                                //                                Text("ID: ${part.vehicle_part_id}", fontSize = 12.sp, color = Color.Gray)
                                Text("Imagen: ${part.image}", fontSize = 12.sp, color = Color.Gray)
                                part.damages?.forEach { damage ->
                                    Text(
                                        "- ${damage.damage_type}: ${damage.description} (${if (damage.fixed == true) "Reparado" else "Sin reparar"})",
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
