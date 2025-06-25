package com.example.tp3_petshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex

// import androidx.compose.material.icons.rounded.


@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val isPreview = LocalInspectionMode.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 12.dp)
            .then(if (!isPreview) Modifier.navigationBarsPadding() else Modifier),
        contentAlignment = Alignment.Center
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(32.dp),
                    clip = false
                )
                .background(Color.White),
            containerColor = Color.White,
            tonalElevation = 0.dp,
        ) {
            val items = listOf(
                Triple("homeScreen", Icons.Default.Home, Icons.Outlined.Home),
                Triple("vehiclesView", Icons.Filled.DirectionsCar, Icons.Outlined.DirectionsCar),
                Triple("profileView", Icons.Default.Person, Icons.Outlined.Person)
            )

            items.forEach { (route, selectedIcon, unselectedIcon) ->
                NavigationBarItem(
                    selected = currentRoute == route,
                    onClick = { onNavigate(route) },
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == route) selectedIcon else unselectedIcon,
                            contentDescription = route,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF7140FD),
                        unselectedIconColor = Color(0xFFAAAAAA),
                        indicatorColor = Color.Transparent
                    ),
                    label = null
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(
        currentRoute = "homeScreen",
        onNavigate = {}
    )
}