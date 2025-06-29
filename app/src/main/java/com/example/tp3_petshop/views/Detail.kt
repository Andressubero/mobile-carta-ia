package com.example.tp3_petshop.views

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.tp3_petshop.components.TopBarSection
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.R
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

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

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    stateDetail?.let {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
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
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Datos generales
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Datos generales", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
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
                            }
                        }

                        // Razones de validación
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Razones de validación", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                val reasonsList = stateDetail!!.validation_reasons?.split(",")
                                reasonsList?.forEach { reason ->
                                    Text("• $reason", style = MaterialTheme.typography.bodySmall)
                                }

                            }
                        }

                        // Partes del vehículo
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Partes del vehículo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(8.dp))
                                stateDetail!!.parts_state?.forEach { part ->
                                    Column(
                                        modifier = Modifier
                                            .padding(vertical = 4.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Divider(thickness = 0.8.dp, color = Color.Gray)
                                        Text("• ${part.vehicle_part_name}", fontWeight = FontWeight.SemiBold)

                                        if (!part.image.isNullOrBlank() && part.image != "No provisto") {
                                            val fullUrl = "http://10.0.2.2:5000/${part.image.replace("\\", "/")}"
                                            println("Full url $fullUrl")
                                            NetworkImage(fullUrl)
                                        } else {
                                            Text(
                                                "Imagen: Dato no provisto",
                                                fontSize = 12.sp,
                                                color = Color.Gray,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(4.dp))

                                        part.damages?.forEach { damage ->
                                            Text(
                                                "- ${damage.damage_type}: ${damage.description} (${if (damage.fixed == true) "Reparado" else "Sin reparar"})",
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
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

@Composable
fun NetworkImage(url: String) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.sedan_front_example),
        error = painterResource(R.drawable.sedan_back_example)
    )
}

