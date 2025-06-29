package com.example.tp3_petshop.views

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.viewmodel.AuthViewModel
import com.example.tp3_petshop.viewmodel.VehicleStateViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainComposable(
    viewModel: VehicleStateViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    TP3PETSHOPTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: ""

        NavHost(navController = navController, startDestination = "initial") {
            composable("initial") {
                SplashView(onGetStartedClick = {
                    navController.navigate("login")
                })
            }

            composable("login") { LoginView(navController, authViewModel) }
            composable("register") { RegisterView(navController, authViewModel) }

            // ✅ Pantalla con BottomNavBar
            composable("homeScreen") {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            onNavigate = { route ->
                                navController.navigate(route) {
                                    popUpTo("homeScreen") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) {
                    HomeScreen(navController, viewModel, authViewModel)
                }
            }

            composable("vehiclesView") {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            onNavigate = { route ->
                                navController.navigate(route) {
                                    popUpTo("homeScreen") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) {
                    VehiclesView(navController)
                }
            }

            composable("profileView") {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            onNavigate = { route ->
                                navController.navigate(route) {
                                    popUpTo("homeScreen") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) {
                    ProfileView({ route -> navController.navigate(route) }, authViewModel)
                }
            }

            // Resto de pantallas sin BottomNavBar
            composable("profileViewSellerMode") {
                ProfileViewSellerMode { route -> navController.navigate(route) }
            }

            composable("settingsView") {
                SettingsView({ navController.navigate(it) }, {})
            }

            composable("privacyView") {
                PrivacyView { navController.navigate(it) }
            }

            composable("securityView") {
                SecurityView { navController.navigate(it) }
            }
            composable("createVehicle") {
                CreateVehicleView(navController)
            }
            composable("faqView") {
                FaqView { navController.navigate(it) }
            }

            composable("notificationSettingView") {
                NotificationView { navController.navigate(it) }
            }

            composable("notificationView") {
                NotificationsListView(navController)
            }

            composable("accountView") {
                AccountView { navController.navigate(it) }
            }

            composable("detail/{id}") { backStackEntry ->
                backStackEntry.arguments?.getString("id")?.let { id ->
                    DetailView(stateId = id, navController = navController, viewModel)
                }
            }

            composable("changePasswordView") {
                ChangePasswordView { navController.navigate(it) }
            }

            composable("changeEmailView") {
                ChangeEmailView { navController.navigate(it) }
            }

            composable("paymentMethodConfigView") {
                PaymentMethodConfigView { navController.navigate(it) }
            }

            composable("payment") {
                ChoosePaymentView(navController)
            }

            composable("paysuccess") {
                PaymentSuccessView(navController)
            }

            composable("searchView") {
                SearchView(navController)
            }

            composable("reportView/{id}") { backStackEntry ->
                backStackEntry.arguments?.getString("id")?.let { id ->
                    ReportView(reportId = id, navController = navController)
                }
            }

            composable("vehicleStateForm/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                if (id != null) {
                    VehicleStateFormView(vehicleId = id, navController)
                } else {
                    HomeScreen(navController, viewModel, authViewModel)
                }
            }

            composable("changeStatus/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                if (id != null) {
                    ChangeStatusView(vehicleId = id, navController = navController)
                }
            }
        }
    }
}
