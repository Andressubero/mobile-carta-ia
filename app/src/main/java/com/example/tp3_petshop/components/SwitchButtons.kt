package com.example.tp3_petshop.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.ButtonOption
import com.example.tp3_petshop.ui.theme.activeButtonBackgroundColor
import com.example.tp3_petshop.ui.theme.activeTextColor
import com.example.tp3_petshop.ui.theme.backgroundColorButtonsContainer
import com.example.tp3_petshop.ui.theme.inactiveButtonColor
import com.example.tp3_petshop.ui.theme.inactiveTextColor

@Composable
fun SwitchButtons(
    options: List<ButtonOption>,
    selectedValue: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentHeight()
        .wrapContentSize()
        .clip(RoundedCornerShape(20.dp))
        .background(backgroundColorButtonsContainer),
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        options.forEach { option ->
            Button(
                onClick = { onChange(option.value) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedValue == option.value) activeButtonBackgroundColor else inactiveButtonColor
                )
            ) {
                Text(
                    option.label,
                    color = if (selectedValue == option.value) activeTextColor else inactiveTextColor
                )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}
