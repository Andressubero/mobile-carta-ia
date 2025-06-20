package com.example.tp3_petshop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.R

@Composable
fun Banner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(209.dp),
        contentAlignment = Alignment.TopStart
    ) {
        // Banner de fondo
        Image(
            painter = painterResource(R.drawable.main_banner),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(159.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        // Imagen circular + texto en un Row superpuesto
        Row(
            modifier = Modifier
                .offset(x = -16.dp, y = -20.dp) // Posicionás todo el bloque
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dog_food),
                contentDescription = "dog food",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(205.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Royal Canin\n" +
                            "Adult Pomeraniann",
                    color = Color.White, // o el color que se vea bien sobre el fondo
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Get an interesting promo here, without conditions",
                    color = Color.White, // o el color que se vea bien sobre el fondo
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }

        }
    }
}