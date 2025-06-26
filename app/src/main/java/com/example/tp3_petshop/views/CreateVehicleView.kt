package com.example.tp3_petshop.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.models.VehicleRequest
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.viewmodel.VehicleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateVehicleView(navController: NavController? = null,
        viewModel: VehicleViewModel = hiltViewModel()) {
    var brand by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var plate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val createResult by viewModel.createResult.collectAsState()
    val vehicleTypes by viewModel.vehicleTypes.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedTypeName by remember { mutableStateOf("") }
    var selectedTypeId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchVehicleTypes()
    }

    LaunchedEffect(createResult) {
        createResult?.let { result ->
            result.onSuccess { response ->
                Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                if (response.success) {
                    navController?.navigate("vehiclesView")
                }
            }.onFailure { error ->
                Toast.makeText(context, error.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val isButtonEnabled = brand.isNotBlank() && model.isNotBlank() && year.isNotBlank() && plate.isNotBlank() && selectedTypeId.isNotBlank()

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(Color(0xFFF1F1F1), shape = MaterialTheme.shapes.medium)
                .padding(24.dp)
                .wrapContentHeight()
                .fillMaxWidth(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Crear Vehiculo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedTypeName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Vehículo") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    vehicleTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.name) },
                            onClick = {
                                selectedTypeName = type.name
                                selectedTypeId = type.id
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FormAuth(
                value = brand,
                onValueChange = { brand = it },
                placeholder = "Marca",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = model,
                onValueChange = { model = it },
                placeholder = "Modelo",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = year,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        year = input
                    }
                },
                placeholder = "Año",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(24.dp))

            FormAuth(
                value = plate,
                onValueChange = { plate = it },
                placeholder = "Patente",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonAuthComp(
                text = "Crear Vehiculo",
                onClick = {
                    val yearInt = year.toIntOrNull()
                    if (yearInt == null) {
                        Toast.makeText(context, "El año ingresado no es válido", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.createVehicle(
                            VehicleRequest(
                                vehicle_type_id = selectedTypeId,
                                model = model,
                                brand = brand,
                                year = yearInt,
                                plate = plate
                            )
                        )
                    }
                    Log.d("CreateVehicle", "Selected Type ID: $selectedTypeId")

                },
                enabled = isButtonEnabled
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController?.popBackStack()
            }) {
                Text("Volver", color = Color(0xFF6C63FF))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateVehicleViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        CreateVehicleView()
    }
}

