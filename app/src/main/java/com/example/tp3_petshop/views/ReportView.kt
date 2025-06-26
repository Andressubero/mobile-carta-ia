package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.TopBarSection
import com.example.tp3_petshop.viewmodel.ReportViewModel

@Composable
fun ReportView(
    reportId: String,
    navController: NavController,
    viewModel: ReportViewModel = hiltViewModel()
) {
    val report by viewModel.report.collectAsState()

    LaunchedEffect(reportId) {
        viewModel.getById(reportId)
    }

    if (reportId == "") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val safeReport = report ?: return

    Scaffold(
        topBar = {
            TopBarSection(
                title = "Resumen del Reporte",
                showFavorite = false,
                isFavorite = false,
                onBackClick = { navController.popBackStack() },
                onFavoriteClick = {}
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        InfoRow("Marca estimada", safeReport.estimated_brand)
                        InfoRow("Modelo estimado", safeReport.estimated_model)
                        InfoRow("Tipo de vehículo", safeReport.vehicle_type)
                        InfoRow("Calidad de imagen", safeReport.image_quality)
                        InfoRow("Daño total estimado", safeReport.total_vehicle_damage_percentage)
                        InfoRow("¿Es la misma unidad?", if (safeReport.is_same_unit_as_reference) "Sí" else "No")
                        InfoRow("Coincidencia con unidad", "${safeReport.same_unit_confidence}%")
                        InfoRow("¿Vehículo válido?", if (safeReport.is_vehicle_valid) "Sí" else "No")
                        InfoRow("Comentarios", safeReport.additional_comments)

                        if (safeReport.validation_reasons.isNotBlank()) {
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "Motivos de validación:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = safeReport.validation_reasons,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Daños detectados",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(safeReport.part_damages) { damage ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(damage.part_name, fontWeight = FontWeight.Bold)
                        Text("Descripción: ${damage.damage_description}")
                        Text("Severidad: ${damage.severity}")
                        Text("Tipo: ${damage.damage_type}")
                        Text("Confianza IA: ${damage.confidence_percentage}%")
                        Text("¿En referencia?: ${if (damage.present_in_reference) "Sí" else "No"}")
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(170.dp)
        )
        Text(text = value)
    }
}
