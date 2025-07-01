package com.example.tp3_petshop.views
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.DamagePopup
import com.example.tp3_petshop.data.hatchbackPoints
import com.example.tp3_petshop.data.motorbikePoints
import com.example.tp3_petshop.data.pickupPoints
import com.example.tp3_petshop.data.sedanPoints
import com.example.tp3_petshop.models.DamagePoint
import com.example.tp3_petshop.models.DamageType
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.PartPosition
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel
import java.text.Normalizer

fun normalize(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        .lowercase()
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun VehicleStateFormSecondStepView(
    vehicleId: String,
    viewModel: VehicleStateViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    vehicleViewModel: VehicleViewModel
) {
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()
    val partStates by viewModel.estadoPartes.collectAsState()
    val isFirstState by viewModel.isFirst.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current
    val density = LocalDensity.current

    var selectedPart by remember { mutableStateOf<PartPosition?>(null) }
    var showPopup by remember { mutableStateOf(false) }
    var damageType by remember { mutableStateOf(DamageType.ABOLLADURA) }
    var description by remember { mutableStateOf("") }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var imageOffset by remember { mutableStateOf(Offset.Zero) }

// 1) Lanza la carga del vehículo con partes
    LaunchedEffect(vehicleId) {
        if (vehicle == null) {
            vehicleViewModel.getVehicleWithPartsById(vehicleId)
        }
        viewModel.isFirstState(vehicleId);
    }

// 2) Cuando cambia vehicle?.parts y partStates está vacío, inicializa estados
    LaunchedEffect(vehicle?.parts, partStates) {
        if (!vehicle?.parts.isNullOrEmpty() && partStates.isEmpty()) {
            println("Inicializando estados de partes")

            val mapped = vehicle!!.parts.map { part ->
                EstadoParte(
                    name = part.name,
                    partId = part.id,
                    damages = listOf(DamagePoint(DamageType.SIN_DANO, ""))
                )
            }
            viewModel.setEstadoPartes(mapped)
            println("✅ EstadoPartes cargados: ${mapped.size}")
        }
    }


    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    val type = vehicle?.type?.let { normalize(it) }.orEmpty()
    val croquisRes = when {
        type.contains("sed") -> R.drawable.sedan_croquis
        type.contains("hatch") -> R.drawable.hatchback_croquis
        type.contains("pick") -> R.drawable.pickup_croquis
        type.contains("moto") -> R.drawable.motorbike_croquis
        else -> R.drawable.sedan_croquis
    }

    val points = when {
        type.contains("sed") -> sedanPoints
        type.contains("pic") -> pickupPoints
        type.contains("mot") -> motorbikePoints
        type.contains("hat") -> hatchbackPoints
        else -> sedanPoints
    }

    fun onSubmit() {
        var filteredStates: List<EstadoParte>
        println("Es primer estado:" + isFirstState + " cantidad de partStates: " + partStates.size)
        if (!isFirstState) {
            filteredStates = partStates.filter { estadoParte ->
                estadoParte.damages.any { it.damageType != DamageType.SIN_DANO }
            }

            viewModel.setEstadoPartes(filteredStates)

            val usedSides: List<String> = points
                .filter { partPosition ->
                    filteredStates.any { it.name == partPosition.name }
                }
                .map { it.side }
                .distinct()
            viewModel.addSides(usedSides)
            println("🧩 estado filtrado → $filteredStates")
            println("🧩 Lados usados → $usedSides")
            if (filteredStates.isNotEmpty()) {
                onNext()
            } else {
                Toast.makeText(context, "Debes seleccionar al menos una parte", Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.setEstadoPartes(partStates)
            val usedSides: List<String> = points
                .filter { partPosition ->
                    partStates.any { it.name == partPosition.name }
                }
                .map { it.side }
                .distinct()
            println("🧩 estado filtrado → $partStates")
            println("🧩 Lados usados → $usedSides")
            viewModel.addSides(usedSides)
            if (partStates.isNotEmpty()) {
                onNext()
            } else {
                Toast.makeText(context, "Debes seleccionar al menos una parte", Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (vehicle == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)

    ) {
        containerSize = IntSize(constraints.maxWidth, constraints.maxHeight)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen con puntos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = croquisRes),
                    contentDescription = "Vista del vehículo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            imageSize = coordinates.size // tamaño real de la imagen
                            imageOffset = coordinates.positionInWindow()
                        },
                    contentScale = ContentScale.FillWidth // ocupa el ancho total
                )

                // Renderizado de puntos
                points.forEach { part ->
                    val isSelected = partStates.find { it.name == part.name }
                        ?.damages?.any { it.damageType != DamageType.SIN_DANO } == true


                    val offsetX = (part.leftPercent / 100f) * imageSize.width
                    val offsetY = (part.topPercent / 100f) * imageSize.height

                    Box(
                        modifier = Modifier
                            .absoluteOffset(
                                x = with(density) { offsetX.toDp() - 10.dp },
                                y = with(density) { offsetY.toDp() - 10.dp }
                            )
                            .size(20.dp)
                            .background(
                                if (isSelected) Color.Red else Color.Blue,
                                shape = CircleShape
                            )
                            .clickable {
                                selectedPart = part
                                damageType = DamageType.ABOLLADURA
                                description = ""
                                showPopup = true
                            }
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            DamagePopup(
                disable = selectedPart == null,
                selectedPartName = selectedPart?.name ?: "Parte no seleccionada",
                damageType = damageType,
                description = description,
                onDamageTypeChange = { damageType = it },
                onDescriptionChange = { description = it },
                onCancel = {
                    showPopup = false
                    selectedPart = null
                },
                onSave = {
                    selectedPart?.let { part ->
                        viewModel.addDamage(
                            part.name,
                            DamagePoint(damageType, description)
                        )
                        //viewModel.addSide(part.side)
                        Toast.makeText(context, "Daño agregado a ${part.name}", Toast.LENGTH_SHORT).show()
                    }
                    damageType = DamageType.SIN_DANO
                    description = ""
                    selectedPart = null
                    showPopup = false
                }
            )

            NavigationButtons(onBack = onBack, onNext = { onSubmit() })
        }
    }
}

@Composable
fun NavigationButtons(
    onBack: () -> Unit,
    onNext: () -> Unit,
    isNextEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(onClick = onBack,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )) {
            Text("Volver")
        }
        Button(onClick = onNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
            ,enabled = isNextEnabled,) {
            Text("Siguiente")
        }
    }
}
