package com.example.tp3_petshop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Meet your animal needs here",
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                lineHeight = 50.sp,
                modifier = Modifier.padding(20.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.onboarding_dog),
                contentDescription = null,
                modifier = Modifier
                    .height(380.dp)
                    .width(500.dp)
            )

            Text(
                text = "Get interesting promos here, register your account immediately so you can meet your animal needs.",
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray
            )

            Image(
                painter = painterResource(id = R.drawable.three_buttons),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(90.dp)
            )

            ButtonAuthComp(
                text = "Get Started",
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
