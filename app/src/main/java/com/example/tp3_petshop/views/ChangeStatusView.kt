package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeStatusView(
    vehicleId: String,
    navController: NavController,
    viewModel: VehicleStateViewModel = hiltViewModel() // <- cambio clave
) {
    var selectedOption by remember { mutableStateOf("PENDIENTE") }
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cambiar estado") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Seleccioná el nuevo estado",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val options = listOf("APROBADA", "DENEGADA", "PENDIENTE")
                            options.forEach { option ->
                                Surface(
                                    onClick = { selectedOption = option },
                                    shape = MaterialTheme.shapes.medium,
                                    tonalElevation = if (selectedOption == option) 4.dp else 1.dp,
                                    color = if (selectedOption == option)
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                    else
                                        MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.CenterStart,
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Text(
                                            text = option,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = if (selectedOption == option) FontWeight.Bold else FontWeight.Normal,
                                            color = if (selectedOption == option)
                                                MaterialTheme.colorScheme.primary
                                            else
                                                MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    viewModel.changeStatus(
                                        ChangeStatusRequest(
                                            id = vehicleId,
                                            validation_state = selectedOption
                                        )
                                    )
                                    navController.popBackStack()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Aceptar")
                            }

                            OutlinedButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Cancelar")
                            }
                        }
                    }
                }
            }
        }
    }
}
