package com.example.tp3_petshop.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.models.Login
import com.example.tp3_petshop.network.RetrofitInstance
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginView(navController: NavController? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

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
                onValueChange = { email = it },
                placeholder = "Usuario",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonAuthComp(
                text = "Ingresar",
                onClick = {
                    if (isButtonEnabled) {
                        loginError = false
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                val response = withContext(Dispatchers.IO) {
                                    RetrofitInstance.authService.login(
                                        Login(username = email, password = password)
                                    )
                                }
                                println("Response: $response")
                                navController?.navigate("homeScreen")
                            } catch (e: Exception) {
                                println("Login error: ${e.message}")
                                loginError = true
                            }
                        }
                    }
                },
                enabled = isButtonEnabled
            )

            if (loginError) {
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


@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        LoginView()
    }
}
