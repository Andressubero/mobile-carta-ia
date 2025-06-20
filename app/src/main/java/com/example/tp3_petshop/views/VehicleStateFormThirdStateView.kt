package com.example.tp3_petshop.views

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tp3_petshop.models.VehicleImage
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel


@Composable
fun VehiclesStateFormThirdStepView(
    viewModel: VehicleStateViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onNext: () -> Unit,
    vehicleViewModel: VehicleViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sides by viewModel.sides.collectAsState()
    val images by viewModel.images.collectAsState()
    val vehicle by vehicleViewModel.vehicleWithPartsById.collectAsState()

    var currentSideKey by remember { mutableStateOf<String?>(null) }

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

            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("Seleccioná la imagen de $label:")

                Button(
                    onClick = {
                        currentSideKey = sideKey
                        launcher.launch("image/*")
                    },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text("Seleccionar imagen")
                }

                // ⚠️ Aquí cambiamos el let{} por un if() dentro del contexto composable
                val bitmap = images[sideKey]?.preview
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Preview de $label",
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(100.dp)
                            .fillMaxWidth()
                    )
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
                onClick = onNext,
                enabled = images.size == sides.size
            ) {
                Text("Crear Estado")
            }
        }
    }
}