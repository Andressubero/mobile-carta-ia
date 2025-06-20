package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.components.TopBarButtonBack
import com.example.tp3_petshop.ui.theme.inactiveTextColor


@Composable
fun PrivacyView(
    onNavigate: (value: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarButtonBack("Privacy") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Terms of Use",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = inactiveTextColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "PetApp Service",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 48.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = inactiveTextColor,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }
}

