package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.MenuItem
import com.example.tp3_petshop.components.MenuComponent
import com.example.tp3_petshop.components.TopBarButtonBack

@Composable
fun SecurityView (
    onNavigate: (value: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarButtonBack("Security") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MenuComponent(
                    title = "Security", items = listOf(
                        MenuItem(Icons.Outlined.Settings, "Change Password", "changePasswordView"),
                        MenuItem(Icons.Outlined.MailOutline, "Change Email", "changeEmailView"),
                    ), fun(value: String) { onNavigate(value) })

            }

        }
    }

}