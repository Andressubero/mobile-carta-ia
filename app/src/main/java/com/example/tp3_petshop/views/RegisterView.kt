package com.example.tp3_petshop.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.FormAuth
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun RegisterView(navController: NavController? = null) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val isButtonEnabled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && agreedToTerms

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column {
            Text("Create New \nAccount", fontSize = 44.sp, lineHeight = 50.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Water is life. Water is a basic human need. In various lines of life, humans need water.",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = fullName,
                onValueChange = {fullName = it},
                placeholder = "Full Name",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = email,
                onValueChange = {email = it},
                placeholder = "Email",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormAuth(
                value = password,
                onValueChange = {password = it},
                placeholder = "Password",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                )
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("I Agree to the ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                        append("Terms of Service")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(" and ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                        append("Privacy Policy")
                    }
                }
                Text(
                    text = annotatedString,
                    modifier = Modifier.clickable {
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 220.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Have an account? ", fontSize = 16.sp)
                TextButton(onClick = {
                    navController?.navigate("login")
                }) {
                    Text("Login", color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        ButtonAuthComp(
            text = "Get Started",
            onClick =  {},
            enabled = isButtonEnabled
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        RegisterView()
    }
}

