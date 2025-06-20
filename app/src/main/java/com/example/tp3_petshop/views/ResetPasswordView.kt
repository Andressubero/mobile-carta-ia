package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun ResetPasswordView(navController: NavController? = null) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isButtonEnabled = newPassword.isNotBlank() && confirmPassword.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column {
            Text("Forgot \nPassword", fontSize = 44.sp, lineHeight = 50.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Water is life. Water is a basic human need. In various lines of life, humans need water.",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = newPassword,
                onValueChange = {newPassword = it},
                placeholder = "New Password",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = confirmPassword,
                onValueChange = {confirmPassword = it},
                placeholder = "Confirm Password",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 250.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Have an account? ", fontSize = 16.sp)
                TextButton(onClick = {
                    navController?.navigate("Login")
                }) {
                    Text("Login", color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        ButtonAuthComp(
            text = "Reset Password",
            onClick =  {},
            enabled = isButtonEnabled
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        ResetPasswordView()
    }
}