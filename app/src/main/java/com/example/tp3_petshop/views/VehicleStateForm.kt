package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel
import com.example.tp3_petshop.viewmodel.VehicleViewModel
import java.util.*


@Composable
fun VehicleStateFormView(
    vehicleId: String,
    navController: NavController,
    viewModel: VehicleStateViewModel = hiltViewModel(),
    vehicleViewModel: VehicleViewModel = hiltViewModel(),
) {
    var currentStep by remember { mutableStateOf(1) }

    when (currentStep) {
        1 -> VehicleStateFormFirstStepView(
            vehicleId = vehicleId,
            onBack = { navController.popBackStack() },
            onNext =  { currentStep = 2 },
            viewModel = viewModel,
            vehicleViewModel = vehicleViewModel
        )

        2 -> VehicleStateFormSecondStepView(
            vehicleId = vehicleId,
            onBack = { currentStep = 2 },
            onNext =  { currentStep = 3 },
            viewModel = viewModel,
            vehicleViewModel = vehicleViewModel)

        3 -> VehiclesStateFormThirdStepView(
            vehicleId = vehicleId,
            onBack = { currentStep = 2 },
            onNavigate =  { navController.navigate("homeScreen") },
            viewModel = viewModel,
            vehicleViewModel = vehicleViewModel)
    }
}
