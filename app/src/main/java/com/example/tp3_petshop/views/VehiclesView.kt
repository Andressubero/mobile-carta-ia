package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    var vehicles  = vehicleViewModel.vehicles.collectAsState();

    LaunchedEffect(Unit) {
        vehicleViewModel.getAll()
    }

    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        bottomBar = { BottomNavBar(currentRoute = "vehiclesView", onNavigate = { route ->
            navController.navigate(route)
        }) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            VehicleList(vehicles = vehicles.value, navController = navController)
        }
    }

}

