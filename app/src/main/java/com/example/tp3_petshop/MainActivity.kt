package com.example.tp3_petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.views.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

                    composable("login") { LoginView(navController) }
                    composable("register") { RegisterView(navController) }

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
                            HomeScreen(navController)
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
                            ProfileView(navController)
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
                            DetailView(stateId = id, navController = navController)
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

                    composable("bestSellerView") {
                        BestSellerView(navController)
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
                            HomeScreen(navController)
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
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP3PETSHOPTheme {
        Greeting("Android")
    }
}
