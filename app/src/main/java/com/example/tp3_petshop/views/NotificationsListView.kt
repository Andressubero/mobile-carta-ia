package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp3_petshop.models.NotificationItem
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.NotificationList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsListView(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabTitles = listOf("Activity", "Seller Mode")

    val activityItems = List(4) {
        NotificationItem("SALE 50%", "Check the details!", R.drawable.product)
    }

    val sellerItems = listOf(
        NotificationItem("You Got New Order!", "Please arrange delivery", R.drawable.product),
        NotificationItem("Momon", "Liked your Product", R.drawable.avatar1),
        NotificationItem("Ola", "Liked your Product", R.drawable.avatar2),
        NotificationItem("Raul", "Liked your Product", R.drawable.avatar3),
        NotificationItem("You Got New Order!", "Please arrange delivery", R.drawable.product),
        NotificationItem("Vito", "Liked your Product", R.drawable.avatar4),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // Switch-style Tab Bar
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFFF1F1F1), shape = MaterialTheme.shapes.large),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { selectedTab = index }
                            .background(
                                color = if (selectedTab == index) Color(0xFF7C3AED) else Color.Transparent,
                                shape = MaterialTheme.shapes.large
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color.White else Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            val items = if (selectedTab == 0) activityItems else sellerItems

            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                items(items) { item ->
                    NotificationList(item)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
