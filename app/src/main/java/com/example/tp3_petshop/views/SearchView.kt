package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp3_petshop.components.MenuComponentClose
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.models.MenuItem
import com.example.tp3_petshop.ui.theme.activeButtonBackgroundColor
import com.example.tp3_petshop.ui.theme.inactiveColor

val optionsSearch = listOf(
    ButtonOption("Food", "food"),
    ButtonOption("Toys", "toys"),
    ButtonOption("Accesories", "accesories")
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(navController: NavController) {
    var selectedTab by remember { mutableStateOf("food") }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedTab = value
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Search", fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Espacio invisible para balancear el ícono de la izquierda
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, // mismo ancho
                            contentDescription = null,
                            tint = Color.Transparent // invisible
                        )
                    }
                }

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = activeButtonBackgroundColor,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = Color.Black,
                        focusedTextColor = activeButtonBackgroundColor,
                        unfocusedTextColor = Color.Black,
                        errorBorderColor = Color.Red
                    ),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("Search your product...",color =  inactiveColor) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(12.dp), // Bordes redondeados
                    singleLine = true
                )
                Spacer(Modifier.height(24.dp))
                TabsButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    optionsSearch,
                    selectedTab,
                    handleChangeSwitchButton,
                    allowFilter = true
                )
                Spacer(Modifier.height(24.dp))
                MenuComponentClose(
                    title = "Recent", items = listOf(
                        MenuItem(Icons.Outlined.Settings, "Royal Canin Persian 500g", ""),
                        MenuItem(Icons.Outlined.MailOutline, "Royal Canin Persian 500g", ""),
                    ), fun(value: String) { })

            }

        }
    }

}