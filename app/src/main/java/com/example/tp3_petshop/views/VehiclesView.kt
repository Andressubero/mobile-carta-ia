package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.components.LocationTopBar
import com.example.tp3_petshop.components.VehicleList
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel

@Composable
fun VehiclesView(
    navController: NavController,
    vehicleViewModel: VehicleViewModel = hiltViewModel()
) {
    val vehicles by vehicleViewModel.vehicles.collectAsState()

    LaunchedEffect(Unit) {
        vehicleViewModel.getAll()
    }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                BottomNavBar(currentRoute = "vehiclesView", onNavigate = { route ->
                    navController.navigate(route)
                })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(Color(0xFFF1F1F1), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    VehicleList(vehicles = vehicles, navController = navController)
                }
            }
        }
    }
}

