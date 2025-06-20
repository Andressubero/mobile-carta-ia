package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.TopBarSection
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel


@Composable
fun DetailView(stateId: String, navController: NavController,
               viewModel: VehicleStateViewModel = hiltViewModel()
              ) {
  val states by viewModel.vehicleStates.collectAsState()
   var quantity by remember { mutableStateOf(1) }
   fun addToCart() {
       navController.navigate("cart")
   }

   LaunchedEffect(stateId) {

   }
    val vehicle = states.find { it.id == stateId }

   if (vehicle != null) {
       Scaffold { innerPadding ->
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(innerPadding)
           ) {
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(horizontal = 24.dp, vertical = 8.dp),
                   verticalArrangement = Arrangement.SpaceBetween
               ) {
                   TopBarSection(
                       title = "Product Detail",
                       showFavorite = false,
                       isFavorite = false,
                       onBackClick = { navController.popBackStack() },
                       onFavoriteClick = {  }
                   )

                   Column(
                       modifier = Modifier
                           .weight(1f)
                           .padding(top = 8.dp),
                       verticalArrangement = Arrangement.spacedBy(16.dp)
                   ) {
                       Text("ID: ${vehicle.id}", style = MaterialTheme.typography.bodyMedium)
                       Text("Marca: ${vehicle.vehicle_brand}")
                       Text("Modelo: ${vehicle.vehicle_model}")
                       Text("Fecha creación: ${vehicle.creation_date}")
                       Text("Fecha declaración: ${vehicle.declared_date}")
                       Text("Estado: ${vehicle.validation_state}")
                       Text("Razones de validación:", fontWeight = FontWeight.Bold)
                       Text(vehicle.validation_reasons)

                       Spacer(modifier = Modifier.height(16.dp))

                       Text("Partes del vehículo:", style = MaterialTheme.typography.titleMedium)
                       vehicle.parts_state.forEach { part ->
                           Divider()
                           Text("Nombre: ${part.vehicle_part_name}")
                           Text("ID: ${part.vehicle_part_id}")
                           Text("Imagen: ${part.image}")
                           part.damages.forEach { damage ->
                               Text("Daño: ${damage.damage_type} - ${damage.description} - Reparado: ${damage.fixed}")
                           }
                       }
                   }

                   // Botón Add to Cart
                   Button(
                       onClick = {addToCart()},
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(56.dp),
                       colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF735BF2)),
                       shape = RoundedCornerShape(30.dp)
                   ) {
                       Text(text = "Volver", fontSize = 18.sp)
                   }
               }
           }
       }
   } else {
       Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
           CircularProgressIndicator()
       }
   }

}
