package com.example.tp3_petshop.views

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.DamagePopup
import com.example.tp3_petshop.models.DamageType
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.PartPosition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tp3_petshop.R
import com.example.tp3_petshop.data.sedanPoints
import com.example.tp3_petshop.models.DamagePoint
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel
import java.text.Normalizer

fun normalize(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        .lowercase()
}



@Composable
fun VehicleStateFormSecondStepView(
    vehicleId: String,
    viewModel: VehicleStateViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onNext: () -> Unit,
    vehicleViewModel: VehicleViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedPart by remember { mutableStateOf<PartPosition?>(null) }
    var showPopup by remember { mutableStateOf(false) }
    var damageType by remember { mutableStateOf(DamageType.ABOLLADURA) }
    var description by remember { mutableStateOf("") }
    var imageWidthPx by remember { mutableStateOf(0f) }
    var imageHeightPx by remember { mutableStateOf(0f) }
    @DrawableRes
    val croquisRes: Int
    var points by remember { mutableStateOf(emptyList<PartPosition>()) }
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()
    val partStates by viewModel.estadoPartes.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    LaunchedEffect(Unit) {
        if (vehicleId.isNotBlank() && vehicle == null) {
            vehicleViewModel.getVehicleWithPartsById(vehicleId)
        }
    }

    LaunchedEffect(vehicle) {
        vehicle?.let {
            val ep = it.parts.map { part ->
                EstadoParte(
                    name = part.name,
                    partId = part.id,
                    damages = listOf(DamagePoint(DamageType.SIN_DANO, ""))
                )
            }
            viewModel.setEstadoPartes(ep)
        }
    }

    val type = vehicle?.type?.let { normalize(it) }.orEmpty()

    croquisRes = when {
        type.contains("sed") -> R.drawable.sedan_croquis
        type.contains("hatch") -> R.drawable.hatchback_croquis
        type.contains("pick") -> R.drawable.pickup_croquis
        type.contains("moto") -> R.drawable.motorbike_croquis
        else -> R.drawable.sedan_croquis
    }

    points = when {
        type.contains("sed") -> sedanPoints
        else -> sedanPoints
    }

    fun onAddDamage(n: String, d: DamagePoint, image: String) {
        viewModel.addDamage(n, d)
        viewModel.addSide(image)
    }


    errorMessage?.let { message ->
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )
    }

    if (vehicle != null && errorMessage.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Image(
                    painter = painterResource(id = croquisRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            imageWidthPx = layoutCoordinates.size.width.toFloat()
                            imageHeightPx = layoutCoordinates.size.height.toFloat()
                        }
                )


                points.forEach { part ->
                    val isSelected = partStates.find { it.name == part.name }
                        ?.damages?.any { it.damageType != DamageType.SIN_DANO } == true

                    Box(
                        modifier = Modifier
                            .offset(
                                x =  part.leftPercent.dp,
                                y = part.topPercent.dp
                            )
                            .size(24.dp)
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

                if (showPopup && selectedPart != null) {
                    selectedPart?.let { part ->
                        val offsetX = percentToOffset(part.leftPercent, imageWidthPx)
                        val offsetY = percentToOffset(part.topPercent, imageHeightPx)

                        DamagePopup(
                            position = Offset(offsetX, offsetY),
                            selectedPartName = part.name,
                            damageType = damageType,
                            description = description,
                            onDamageTypeChange = { damageType = it },
                            onDescriptionChange = { description = it },
                            onCancel = { showPopup = false },
                            onSave = {
                                onAddDamage(
                                    n = part.name,
                                    d = DamagePoint(damageType, description),
                                    image = part.side
                                )
                                Toast.makeText(context, "Daño agregado a ${part.name}", Toast.LENGTH_SHORT).show()
                                showPopup = false
                            }
                        )
                    }
                }


            }
        }

    }
    NavigationButtons(onBack = { onBack() }, onNext = {onNext() })
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
        OutlinedButton(onClick = onBack) {
            Text("Volver")
        }
        Button(onClick = onNext, enabled = isNextEnabled) {
            Text("Siguiente")
        }
    }
}

fun percentToOffset(percent: Float, sizePx: Float): Float {
    return (percent / 100f) * sizePx
}


