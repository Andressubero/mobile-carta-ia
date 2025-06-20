package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.RoundedTextField
import com.example.tp3_petshop.components.TopBarButtonBack

@Composable
fun AccountView(
    onNavigate: (value: String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopBarButtonBack("Account") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(209.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // Fondo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .height(159.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_background_gray),
                        contentDescription = "Fondo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar fondo",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .background(Color.White, shape = CircleShape)
                            .padding(4.dp)
                            .clickable { /* acción */ }
                    )
                }
                // Avatar con ícono de edición
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = 109.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_avatar),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )

                    Box(
                        modifier = Modifier
                            .size(28.dp) // Tamaño total del fondo circular
                            .background(Color.White, shape = CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = (0).dp, y = -2.dp) // Ajuste fino opcional
                            .clickable { /* acción */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar avatar",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                    }

                }

            }


            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "Abduldul",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(16.dp))
            RoundedTextField(
                label = "Name",
                value = name,
                onValueChange = { name = it },
                placeholder = "Name"
            )
            Spacer(Modifier.height(16.dp))
            RoundedTextField(
                label = "Username",
                value = username,
                onValueChange = { username = it },
                placeholder = "Username"
            )
            Spacer(Modifier.height(16.dp))
            RoundedTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "Email"
            )
            Spacer(Modifier.weight(1f))
            ButtonAuthComp(
                "Save Changes",
                fun(){},
                true
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AccountPreview() {
    AccountView(fun(value: String){  })
}