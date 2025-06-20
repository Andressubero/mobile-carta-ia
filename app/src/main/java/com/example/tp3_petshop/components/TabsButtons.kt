package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.ui.theme.activeBackgroundButtonColor
import com.example.tp3_petshop.ui.theme.inactiveBackgroundButtonColor
import com.example.tp3_petshop.ui.theme.inactiveColor


@Composable
fun DualIconButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = inactiveBackgroundButtonColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = "Avanzar",
                modifier = Modifier.size(12.dp),
                tint = Color.Black,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Retroceder",
                modifier = Modifier.size(12.dp),
                tint = Color.Black,
            )
        }
    }
}

@Composable
fun TabsButton(
    modifier: Modifier = Modifier,
    options: List<ButtonOption>,
    selected: String,
    handleChange: (String) -> Unit = {},
    allowFilter: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (allowFilter) {
            DualIconButton {}
        }
        options.forEach { option ->
            Button(
                onClick = { handleChange(option.value) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected == option.value) activeBackgroundButtonColor else inactiveBackgroundButtonColor
                )
            ) {
                Text(
                    text = option.label,
                    color = if (selected != option.value) inactiveColor else Color.White
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable()
fun ButtonPreview() {
    DualIconButton {  }
}
