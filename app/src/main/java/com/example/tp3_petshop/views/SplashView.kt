package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.R
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.ui.theme.TP3PETSHOPTheme

@Composable
fun SplashView(onGetStartedClick: () -> Unit = {}) {
    // Gradient background
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB3CFFB), Color(0xFF7A6FF1))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(24.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_bdtglobal),
                contentDescription = "Logo BDT",
                modifier = Modifier
                    .size(220.dp), // o el tamaño que desees
                contentScale = ContentScale.Fit // Podés probar también Crop
            )

            Text(
                text = "Carta de Daño IA",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            ButtonAuthComp(
                text = "Comenzar",
                onClick = onGetStartedClick,
                enabled = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashViewPreview() {
    TP3PETSHOPTheme(darkTheme = false, dynamicColor = false) {
        SplashView()
    }
}
