package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormAuth(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboard: KeyboardOptions,
) {
    val visualTransformation = if (keyboard.keyboardType == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFF6C63FF),
            unfocusedIndicatorColor = Color.LightGray,
            disabledIndicatorColor = Color.LightGray,
            errorIndicatorColor = Color.Red
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = keyboard,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            color = Color(0xFF6C63FF),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}