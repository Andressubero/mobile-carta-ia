package com.example.tp3_petshop.views


import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.RoundedTextField
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.viewmodel.AuthViewModel

val optionsProfile = listOf(
    ButtonOption("Saved", "saved"),
    ButtonOption("Edit Profile", "editprofile")
)

@Composable
fun ProfileView(
    navigate: (value: String) -> Unit,
    authViewModel: AuthViewModel
) {
    val user by authViewModel.user.collectAsState()
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val error: String? by authViewModel.errorMessage.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            authViewModel.clearError() // Asegúrate de tener esta función en el viewModel
        }
    }

    // Reglas
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    val isLengthValid = password.length in 1..15
    val passwordsMatch = password == confirmPassword

    val isPasswordValid = hasUppercase && hasLowercase && hasDigit && hasSpecialChar && isLengthValid && passwordsMatch

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(currentRoute = "profileView", onNavigate = navigate)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    "Perfil",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (user != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = user?.email.toString(),
                                onValueChange = {},
                                label = { Text("Email") },
                                singleLine = true,
                                enabled = true,
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            RoundedTextField(
                                label = "Nueva contraseña",
                                value = password,
                                onValueChange = { password = it },
                                placeholder = "Contraseña"
                            )

                            RoundedTextField(
                                label = "Confirmar contraseña",
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                placeholder = "Confirmar contraseña"
                            )

                            // Reglas visuales
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                PasswordRule("Una letra mayúscula", hasUppercase)
                                PasswordRule("Una letra minúscula", hasLowercase)
                                PasswordRule("Un número", hasDigit)
                                PasswordRule("Un carácter especial", hasSpecialChar)
                                PasswordRule("Máximo 15 caracteres", isLengthValid)
                                PasswordRule("Las contraseñas coinciden", passwordsMatch)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ButtonAuthComp(
                        text = "Cambiar contraseña",
                        onClick = {
                            authViewModel.changePassword(password) {
                                Toast.makeText(context, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show()
                                password = ""
                                confirmPassword = ""
                            }
                        },
                        enabled = isPasswordValid
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonAuthComp(
                        text = "Logout",
                        onClick = { authViewModel.logout(onSuccess = { navigate("initial") }) },
                        enabled = true
                    )
                } else {
                    Text(
                        "No hay datos para mostrar",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun PasswordRule(text: String, passed: Boolean) {
    val color = if (passed) Color(0xFF2E7D32) else Color(0xFFD32F2F)
    val icon = if (passed) Icons.Default.Check else Icons.Default.Close

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(text, fontSize = 13.sp, color = color)
    }
}
