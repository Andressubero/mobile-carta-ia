package com.example.tp3_petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp3_petshop.views.AccountView
import com.example.tp3_petshop.views.BestSellerView
import com.example.tp3_petshop.views.ChangeEmailView
import com.example.tp3_petshop.views.ChangePasswordView
import com.example.tp3_petshop.views.ChoosePaymentView
import com.example.tp3_petshop.views.CreateVehicleView
import com.example.tp3_petshop.views.FaqView
import com.example.tp3_petshop.views.HomeScreen
import com.example.tp3_petshop.views.NotificationView
import com.example.tp3_petshop.views.PrivacyView
import com.example.tp3_petshop.views.ProfileView
import com.example.tp3_petshop.views.ProfileViewSellerMode
import com.example.tp3_petshop.views.SecurityView
import com.example.tp3_petshop.views.SettingsView
import com.example.tp3_petshop.views.LoginView
import com.example.tp3_petshop.views.SplashView
import com.example.tp3_petshop.views.RegisterView
import com.example.tp3_petshop.views.DetailView
import com.example.tp3_petshop.views.NotificationsListView
import com.example.tp3_petshop.views.PaymentMethodConfigView
import com.example.tp3_petshop.views.PaymentSuccessView
import com.example.tp3_petshop.views.ReportView
import com.example.tp3_petshop.views.SearchView
import com.example.tp3_petshop.views.VehicleStateFormFirstStepView
import com.example.tp3_petshop.views.VehicleStateFormSecondStepView
import com.example.tp3_petshop.views.VehicleStateFormView
import com.example.tp3_petshop.views.VehiclesStateFormThirdStepView
import com.example.tp3_petshop.views.VehiclesView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP3PETSHOPTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "initial") {
                    composable("initial") {
                        SplashView(
                            onGetStartedClick = {
                                navController.navigate("login")
                            }
                        )
                    }
                    composable("login") {
                        LoginView(navController)
                    }
                    composable("register") {
                        RegisterView(navController)
                    }
                    composable("profileViewSellerMode") {
                        ProfileViewSellerMode(
                            fun (route: String){
                                navController.navigate(route)
                            },
                        )
                    }
                    composable("profileView") {
                        ProfileView(
                            fun(route: String) {
                                navController.navigate(route)
                            }
                        )
                    }
                    composable("settingsView") {
                        SettingsView(
                            fun(value: String) {
                                navController.navigate(value)
                            },
                            fun() {}
                        )
                    }
                    composable("privacyView") {
                        PrivacyView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("securityView") {
                        SecurityView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("createVehicle") {
                        CreateVehicleView(navController)
                    }
                    composable("faqView") {
                        FaqView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("notificationSettingView") {
                        NotificationView (
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("notificationView") {
                        NotificationsListView(navController)
                    }

                    composable("accountView") {
                        AccountView(
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("homeScreen") {
                        HomeScreen(navController)
                    }
                    composable("detail/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        if (id != null) {
                            DetailView(stateId = id, navController = navController)
                        }
                    }


                    composable("changePasswordView") {
                        ChangePasswordView(
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("changeEmailView") {
                        ChangeEmailView(
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("paymentMethodConfigView") {
                        PaymentMethodConfigView(
                            fun(value: String) {
                                navController.navigate(value)
                            }
                        )
                    }
                    composable("payment") {
                        ChoosePaymentView(navController)
                    }
                    composable("paysuccess") {
                        PaymentSuccessView(navController)
                    }
                    composable("searchView") {
                        SearchView(
                            navController
                        )
                    }
                    composable("bestSellerView") {
                        BestSellerView(
                            navController
                        )
                    }
                    composable("reportView/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        if (id != null) {
                            ReportView(reportId = id, navController = navController)
                        }
                    }
                    composable("vehicleStateForm/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        if (id != null) {
                            VehicleStateFormView(vehicleId = id, navController)
                        }else {
                            HomeScreen(navController)
                        }
                    }
                    composable("vehiclesView") {
                        VehiclesView(
                           navController = navController

                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP3PETSHOPTheme {
        Greeting("Android")
    }
}