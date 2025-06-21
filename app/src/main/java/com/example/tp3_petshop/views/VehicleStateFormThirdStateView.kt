package com.example.tp3_petshop.views

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.R
import com.example.tp3_petshop.models.DamagePoint
import com.example.tp3_petshop.models.DamageType
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.VehicleImage
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel


@Composable
fun VehiclesStateFormThirdStepView(
    vehicleId: String,
    viewModel: VehicleStateViewModel,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    vehicleViewModel: VehicleViewModel
) {
    val context = LocalContext.current
    val sides by viewModel.sides.collectAsState()
    val images by viewModel.images.collectAsState()
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var currentSideKey by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        if (vehicle == null) {
            vehicleViewModel.getVehicleWithPartsById(vehicleId)
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        val sideKey = currentSideKey
        if (uri != null && sideKey != null) {
            try {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                } else {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }

                viewModel.addImage(
                    sideKey,
                    VehicleImage(file = uri, preview = bitmap.asImageBitmap())
                )
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val sideLabels = mapOf(
        "FRONT" to "Frente",
        "BACK" to "Parte trasera",
        "LATERAL_LEFT" to "Lateral izquierdo",
        "LATERAL_RIGHT" to "Lateral derecho",
        "TOP" to "Techo"
    )

    val exampleImages = mapOf(
        "FRONT" to R.drawable.sedan_front_example,
        "BACK" to R.drawable.sedan_back_example,
        "LATERAL_LEFT" to R.drawable.sedan_left_example,
        "LATERAL_RIGHT" to R.drawable.sedan_right_example,
        "TOP" to R.drawable.sedan_top_example
    )

    fun onSubmit() {
        vehicle?.let {
            println("✅ Vehicle usado: ${it.id} ${it.brand} ${it.model}")
            viewModel.createVehicleState(
                vehicleId = vehicleId,
                brand = it.brand,
                model = it.model,
                onSuccess = onNavigate
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Subí imágenes de los lados afectados",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            sides.forEach { sideKey ->
                val label = sideLabels[sideKey] ?: sideKey
                val exampleRes = exampleImages[sideKey]
                    ?: R.drawable.sedan_top_example // usá un placeholder si falta alguno

                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text("Foto de $label", style = MaterialTheme.typography.bodyLarge)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Imagen de ejemplo
                        Image(
                            painter = painterResource(id = exampleRes),
                            contentDescription = "Ejemplo $label",
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                                .padding(end = 4.dp),
                            contentScale = ContentScale.Crop
                        )

                        // Imagen subida (preview)
                        val bitmap = images[sideKey]?.preview
                        if (bitmap != null) {
                            Image(
                                bitmap = bitmap,
                                contentDescription = "Preview $label",
                                modifier = Modifier
                                    .weight(1f)
                                    .height(100.dp)
                                    .padding(start = 4.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(100.dp)
                                    .padding(start = 4.dp)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Sin imagen")
                            }
                        }
                    }

                    Button(
                        onClick = {
                            currentSideKey = sideKey
                            launcher.launch("image/*")
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Seleccionar imagen")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onBack) {
                    Text("Anterior")
                }
                Button(
                    onClick = { onSubmit() },
                    enabled = !isLoading && images.size == sides.size
                ) {
                    Text("Crear Estado")
                }
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}
