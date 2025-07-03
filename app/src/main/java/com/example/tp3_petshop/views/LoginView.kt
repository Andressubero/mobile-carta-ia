package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.viewmodel.AuthViewModel

@Composable
fun LoginView(navController: NavController? = null, viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val error: String? by viewModel.errorMessage.collectAsState()

    val isButtonEnabled = email.isNotBlank() && password.isNotBlank()

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(Color(0xFFF1F1F1), shape = MaterialTheme.shapes.medium)
                .padding(24.dp)
                .wrapContentHeight()
                .fillMaxWidth(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Iniciar sesión",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            FormAuth(
                value = email,
                onValueChange = { if (it.length <= 40) email = it },
                placeholder = "Usuario",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = password,
                onValueChange = { if (it.length <= 15) password = it },
                placeholder = "Contraseña",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonAuthComp(
                text = "Ingresar",
                onClick = {
                    if (isButtonEnabled) {
                        viewModel.login(
                            email,
                            password,
                            onSuccess =  { navController?.navigate("homeScreen") }
                            )
                    }
                },
                enabled = isButtonEnabled
            )

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Error: Usuario o contraseña incorrectos",
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿No tenés cuenta? ", fontSize = 16.sp)
                TextButton(onClick = {
                    navController?.navigate("register")
                }) {
                    Text("Registrarse", color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            TextButton(onClick = {
                navController?.popBackStack()
            }) {
                Text("Volver", color = Color(0xFF6C63FF))
            }
        }
    }
}

