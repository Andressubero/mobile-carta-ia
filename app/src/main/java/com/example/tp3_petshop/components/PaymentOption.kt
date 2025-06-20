package com.example.tp3_petshop.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentOption(
    title: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (selected) Color(0xFF7A4DFF) else Color.LightGray
    val textColor = if (enabled) (if (selected) Color(0xFF7A4DFF) else Color.Black) else Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, color = textColor, fontSize = 16.sp)
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF7A4DFF),
                    unselectedColor = Color.LightGray,
                    disabledSelectedColor = Color.LightGray,
                    disabledUnselectedColor = Color.LightGray
                )
            )
        }
    }
}