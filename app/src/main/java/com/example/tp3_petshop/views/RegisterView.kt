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
import com.example.tp3_petshop.models.LoginRequest
import com.example.tp3_petshop.models.Register
import com.example.tp3_petshop.network.RetrofitInstance
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegisterView(navController: NavController? = null, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registerError by remember { mutableStateOf(false) }
    var registerMessage by remember { mutableStateOf("") }
    val error: String? by authViewModel.errorMessage.collectAsState()

    val isButtonEnabled = email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword

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
                "Registrarse",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            FormAuth(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña:",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormAuth(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirmar contraseña:",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonAuthComp(
                text = "Registrarse",
                onClick = {
                    if (isButtonEnabled) {
                        authViewModel.register(username = email, password = password, onSuccess = {navController?.navigate("login")})
                    }
                },
                enabled = isButtonEnabled
            )

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error!!,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController?.popBackStack()
            }) {
                Text("Volver", color = Color(0xFF6C63FF))
            }
        }
    }
}
