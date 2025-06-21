package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.Banner
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.components.LocationTopBar
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.models.ButtonOption

val optionsHomeScreen = listOf(
    ButtonOption("Food", "food"),
    ButtonOption("Toys", "toys"),
    ButtonOption("Accesories", "accesories")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentLocation = remember { mutableStateOf("Jebres, Surakarta") }
    var selectedTab by remember { mutableStateOf("food") }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedTab = value
    }
    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            LocationTopBar(
                currentLocation = currentLocation.value,
                onLocationClick = { showBottomSheet = true },
                onNotificationClick = {
                    navController.navigate("notificationView")
                },
                onSearchClick = {
                    navController.navigate("searchView")
                }

            )
        },

        bottomBar = { BottomNavBar(currentRoute = "homeView", onNavigate = { route ->
            navController.navigate(route)
        }) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            VehicleStateList(navController = navController)
        }
    }
}
