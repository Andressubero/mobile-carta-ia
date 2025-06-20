package com.example.tp3_petshop.views


import androidx.compose.foundation.Image
import androidx.compose.runtime.*
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.tp3_petshop.components.ProductCard
import com.example.tp3_petshop.components.SwitchButtons
import com.example.tp3_petshop.components.TabsButton
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme
import com.example.tp3_petshop.viewmodel.ProductUiState
import com.example.tp3_petshop.viewmodel.ProductViewModel


val options = listOf(
    ButtonOption("Product", "product"),
    ButtonOption("Sold", "sold"),
    ButtonOption("Stats", "stats")
)
val switchOptions = listOf(
    ButtonOption("Profile", "profile"),
    ButtonOption("Seller Mode", "seller"),
)

@Composable
fun ProfileViewSellerMode(
    navigate: (value: String) -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf("product") }
    var selectedSwitch by remember { mutableStateOf("seller") }

    val handleChangeTabs: (String) -> Unit = { value ->
        selectedTab = value
    }
    val handleChangeSwitchButton: (String) -> Unit = { value ->
        selectedSwitch = value
        navigate("profileView")
    }

    Scaffold { innerPadding ->
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
                        painter = painterResource(R.drawable.profile_background),
                        contentDescription = "Fondo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Imagen circular superpuesta
                Image(
                    painter = painterResource(R.drawable.profile_photo_p),
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
                    text = "Pittashop",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
            }
            Row(
                modifier= Modifier.width(224.dp)
                    .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Text(
                        text = "109",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Followers",
                        fontSize = 12.sp
                    )
                }
                Column {
                    Text(
                        text = "992",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Following",
                        fontSize = 12.sp
                    )

                }
                Column {
                    Text(
                        text = "80",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sales",
                        fontSize = 12.sp
                    )

                }
            }
            TabsButton(
                Modifier.align(Alignment.CenterHorizontally),
                options,
                selectedTab,
                handleChange = handleChangeTabs
            )
            Column {
                when (uiState) {
                    is ProductUiState.Loading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is ProductUiState.Error -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Error al cargar los productos")
                        }
                    }
                    is ProductUiState.Success -> {
                        val products = (uiState as ProductUiState.Success).products

                        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                items(products) { product ->
                                    ProductCard(product = product) {
                                        navigate("detail/${product.id}")
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable

fun ProfileViewSellerModePreview () {
    TP3PETSHOPTheme (darkTheme = false, dynamicColor = false) {
        ProfileViewSellerMode({})
    }
}