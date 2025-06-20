package com.example.tp3_petshop.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.components.MenuComponent
import com.example.tp3_petshop.components.TopBarButtonBack
import com.example.tp3_petshop.models.MenuItem
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun SettingsView(
    onNavigate: (value: String) -> Unit,
    onLogout: () -> Unit
) {

    fun onOptionClick (value: String){
        onNavigate(value)
    }

    Scaffold(
        topBar = {
            TopBarButtonBack("Settings Page") { onNavigate("profileView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            // Section: Account
            MenuComponent(
                title = "Account", items = listOf(
                    MenuItem(Icons.Outlined.Person, "Account", "accountView"),
                    MenuItem(Icons.Outlined.Home, "Address", "settingsView"),
                    MenuItem(Icons.Outlined.Notifications, "Notification", "notificationSettingView"),
                    MenuItem(Icons.Outlined.AccountBox, "Payment Method", "paymentMethodConfigView"),
                    MenuItem(Icons.Outlined.Info, "Privacy", "privacyView"),
                    MenuItem(Icons.Outlined.Lock, "Security", "securityView")
                ), fun(value: String) { onOptionClick(value) })

            Spacer(Modifier.height(24.dp))

            // Section: Help
            MenuComponent(
                title = "Help", items = listOf(
                    MenuItem(Icons.Outlined.Call, "Contact Us", "settingsView"),
                    MenuItem(Icons.Outlined.Menu, "FAQ", "faqView"),
                ), fun(value: String) { onOptionClick(value) })

            Spacer(Modifier.weight(1f))

            // Logout button
            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9A52FF),
                        shape = RoundedCornerShape(24.dp)
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    "Log Out",
                    color = Color(0xFF9A52FF),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable

fun SettingPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        SettingsView(
            fun(value: String) {
            },
            fun() {}
        )
    }
}