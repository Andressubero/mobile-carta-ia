package com.example.tp3_petshop.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.BottomNavBar
import com.example.tp3_petshop.components.SwitchButtons
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

val optionsProfile = listOf(
    ButtonOption("Saved", "saved"),
    ButtonOption("Edit Profile", "editprofile")
)

@Composable
fun ProfileView(
    navigate: (value: String) -> Unit,
){
    Text("ProfileView")
}

/*
@Composable
fun ProfileView(
    navigate: (value: String) -> Unit,
    combinedViewModel: CombinedViewModel = hiltViewModel()

) {
    val uiState by combinedViewModel.favoriteProducts.collectAsState()
    var selectedTab by remember { mutableStateOf("saved") }
    var selectedSwitch by remember { mutableStateOf("profile") }

    val handleChangeTabs: (String) -> Unit = { value ->
        selectedTab = value
        if (value === "editprofile") navigate("settingsView")
    }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedSwitch = value
        navigate("profileViewSellerMode")
    }

    Scaffold(
        bottomBar = { BottomNavBar(currentRoute = "profileView", onNavigate = { route ->
            navigate(route)
        }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SwitchButtons(
                switchOptions,
                selectedSwitch,
                handleChangeSwitchButton,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Fondo + imagen superpuesta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(209.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
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
                }

                // Imagen circular superpuesta
                Image(
                    painter = painterResource(R.drawable.profile_avatar),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = 109.dp) // baja desde el top, pero sobresale
                        .clip(CircleShape)
                )
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
            TabsButton(
                Modifier.align(Alignment.CenterHorizontally),
                optionsProfile,
                selectedTab,
                handleChange = handleChangeTabs
            )
            Column {
                if (uiState.isNotEmpty()) {
                    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            items(uiState) { product ->
                                ProductCard(product = product) {
                                    navigate("detail/${product.id}")
                                }
                            }
                        }
                    }
                } else {
                    Text("No hay productos favoritos", modifier = Modifier.padding(16.dp))
                }

            }

        }
    }
}



@Preview(showBackground = true)
@Composable

fun ProfileViewPreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        ProfileView({}, viewModel())
    }
}

 */