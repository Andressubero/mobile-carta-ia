package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.RoundedTextField
import com.example.tp3_petshop.components.TopBarButtonBack

@Composable
fun ChangeEmailView(
    onNavigate: (value: String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopBarButtonBack("Change Email") { onNavigate("securityView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            Spacer(Modifier.height(16.dp))
            RoundedTextField(
                label = "New Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "Email"
            )
            Spacer(Modifier.weight(1f))
            ButtonAuthComp(
                "Save",
                fun() {},
                true
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChangeEmailViewPreview() {
    ChangeEmailView(fun(value: String){  })
}