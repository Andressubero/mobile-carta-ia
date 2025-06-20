package com.example.tp3_petshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.outlined.DirectionsCar

// import androidx.compose.material.icons.rounded.


@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "homeView",
            onClick = { onNavigate("homeScreen") },
            icon = {
                Icon(
                    if (currentRoute == "homeView") Icons.Default.Home else Icons.Outlined.Home,
                    contentDescription = "Inicio",
                    modifier = Modifier.size(35.dp)
                )
            },
            label = {
                if (currentRoute == "homeView") {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF7140FD), shape = CircleShape)
                    )
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7140FD),
                unselectedIconColor = Color(0xFFAAAAAA), // o tu inactiveTextColor
                indicatorColor = Color.Transparent
            )
        )


        NavigationBarItem(
            selected = currentRoute == "view",
            onClick = { onNavigate("homeScreen") },
            icon = {
                Icon(
                    if (currentRoute == "view") Icons.Outlined.Email else Icons.Outlined.Email,
                    contentDescription = "Inicio",
                    modifier = Modifier.size(35.dp)
                )
            },
            label = {
                if (currentRoute == "view") {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF7140FD), shape = CircleShape)
                    )
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7140FD),
                unselectedIconColor = Color(0xFFAAAAAA), // o tu inactiveTextColor
                indicatorColor = Color.Transparent
            )
        )




        NavigationBarItem(
            selected = currentRoute == "vehiclesView",
            onClick = { onNavigate("vehiclesView") },
            icon = {
                Icon(
                    if (currentRoute == "vehiclesView") Icons.Filled.DirectionsCar else Icons.Outlined.DirectionsCar,
                    contentDescription = "vehiclesView",
                    modifier = Modifier.size(35.dp)
                )
            },
            label = {
                if (currentRoute == "vehiclesView") {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF7140FD), shape = CircleShape)
                    )
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7140FD),
                unselectedIconColor = Color(0xFFAAAAAA),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = currentRoute == "profileView",
            onClick = { onNavigate("profileView") },
            icon = {
                Icon(
                    if (currentRoute == "profileView") Icons.Default.Person else Icons.Outlined.Person,
                    contentDescription = "profile",
                    modifier = Modifier.size(35.dp),
                )
            },
            label = {
                if (currentRoute == "profileView") {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF7140FD), shape = CircleShape)
                    )
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7140FD),
                unselectedIconColor = Color(0xFFAAAAAA),
                indicatorColor = Color.Transparent
            )
        )
    }
}
