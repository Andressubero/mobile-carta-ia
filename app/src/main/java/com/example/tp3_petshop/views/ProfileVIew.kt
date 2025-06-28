package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.tp3_petshop.network.RetrofitInstance

@Composable
fun ProfileView(
    navController: NavController,
) {
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                isLoading = true
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        withContext(Dispatchers.IO) {
                            RetrofitInstance.authService.logout()
                        }
                        navController.navigate("login")
                    } catch (e: Exception) {
                        // Podés mostrar un mensaje de error si querés
                        e.printStackTrace()
                    } finally {
                        isLoading = false
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("Cerrar sesión", color = Color.White)
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.BottomCenter).padding(32.dp)
            )
        }
    }
}