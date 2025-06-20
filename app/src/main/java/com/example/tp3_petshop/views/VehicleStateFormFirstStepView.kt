package com.example.tp3_petshop.views

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    viewModel: VehicleStateViewModel = hiltViewModel(),
    vehicleViewModel: VehicleViewModel = hiltViewModel()
) {
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val today = remember { dateFormat.format(Date()) }
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()
    val rawDate by viewModel.selectedDate.collectAsState()
    val date = rawDate ?: ""
    val errorMessage by vehicleViewModel.error.collectAsState()

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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
    if (errorMessage.isNullOrBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Crear estado del vehículo", style = MaterialTheme.typography.titleLarge)

            if (vehicle == null) {
                // ⏳ Mostrar loading o fallback si el vehículo aún no está cargado
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                // ✅ Mostrar formulario completo si el vehículo ya está disponible
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

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(onClick = onBack) {
                        Text("Volver")
                    }

                    Button(
                        onClick = {
                            viewModel.setDate(date)
                            onNext()
                        },
                        enabled = date.isNotBlank()
                    ) {
                        Text("Siguiente")
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