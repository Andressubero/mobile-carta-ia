package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.viewmodel.VehicleViewModel

@Composable
fun VehicleStateFormFirstStepView(
    vehicleId: String,
    onBack: () -> Unit,
    onNext: () -> Unit,
    viewModel: VehicleStateViewModel,
    vehicleViewModel: VehicleViewModel
) {
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val today = remember { dateFormat.format(Date()) }
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()
    val rawDate by viewModel.selectedDate.collectAsState()
    val date = rawDate ?: ""
    val errorMessage by vehicleViewModel.error.collectAsState()

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    LaunchedEffect(Unit) {

        if (vehicleId.isNotBlank()) {
            vehicleViewModel.getVehicleWithPartsById(vehicleId)
        }

        if (viewModel.selectedDate.value.isNullOrBlank()) {
            viewModel.setDate(today)
        }
    }

    if (!errorMessage.isNullOrBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp)
            ){
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    vehicleViewModel.getVehicleWithPartsById(vehicleId)
                }) {
                    Text("Reintentar")
                }

            }
        }
    }
    if (errorMessage.isNullOrBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(horizontal = 24.dp, vertical = 48.dp)
                ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Crear estado del vehículo",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge)

            if (vehicle == null) {

                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Datos del vehículo",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = vehicle!!.brand,
                                onValueChange = {},
                                label = { Text("Marca") },
                                singleLine = true,
                                enabled = true,
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = vehicle!!.model,
                                onValueChange = {},
                                label = { Text("Modelo") },
                                singleLine = true,
                                enabled = true,
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            VehicleDatePickerField(
                                date = date,
                                onDateSelected = { viewModel.setDate(it) }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = onBack,
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Volver")
                        }

                        Button(
                            onClick = {
                                viewModel.setDate(date)
                                onNext()
                            },
                            enabled = date.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Siguiente")
                        }
                    }
                }
            }

        }

    }
}

    @Composable
fun VehicleDatePickerField(
    date: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val formatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        android.app.DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(formatter.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis() // 🔒 Restringe al día actual
        }
    }

    OutlinedTextField(
        value = date,
        onValueChange = {},
        label = { Text("Fecha del estado declarado") },
        placeholder = { Text("yyyy-MM-dd") },
        readOnly = true,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }, // Este gesto es muy natural para usuarios
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
            }
        },
        isError = date.isBlank(),
        supportingText = {
            if (date.isBlank()) Text("Este campo es obligatorio")
        }
    )
}