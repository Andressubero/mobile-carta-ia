package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.ButtonAuthComp
import com.example.tp3_petshop.components.RoundedTextField
import com.example.tp3_petshop.components.TopBarButtonBack
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.material3.CreditCardVisualTransformation

@Composable
fun PaymentMethodConfigView(onNavigate: (value: String) -> Unit) {
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }

    var showErrors by remember { mutableStateOf(false) }
    var hasSubmitted by remember { mutableStateOf(false) }

    fun shouldShowError(value: String): Boolean = showErrors && value.isBlank()

    fun onFieldFocusLost(focused: Boolean) {
        if (!focused && hasSubmitted) showErrors = true
    }

    val isFormValid = cardNumber.isNotBlank() && cardName.isNotBlank() &&
            cvv.isNotBlank() && expirationDate.isNotBlank()

    val creditCardOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int) = offset + (offset / 4)
        override fun transformedToOriginal(offset: Int) = offset - (offset / 5)
    }

    fun creditCardVisualTransformation() = VisualTransformation { text ->
        val trimmed = text.text.filter { it.isDigit() }
        val formatted = trimmed.chunked(4).joinToString(" ")
        TransformedText(AnnotatedString(formatted), creditCardOffsetMapping)
    }

    Scaffold(
        topBar = {
            TopBarButtonBack("Payment Method") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            RoundedTextField(
                label = "",
                value = cardNumber,
                onValueChange = { cardNumber = it },
                placeholder = "Card Number",
                //transformation = CreditCardVisualTransformation,
                isError = showErrors && cardNumber.isBlank(),
                errorMessage = if (showErrors && cardNumber.isBlank()) "Required Field" else null,
                onFocusChanged = ::onFieldFocusLost
            )

            Spacer(Modifier.height(16.dp))

            RoundedTextField(
                label = "",
                value = cardName,
                onValueChange = { cardName = it },
                placeholder = "Card Name",
                isError = showErrors && cardName.isBlank(),
                errorMessage = if (showErrors && cardName.isBlank()) "Required Field" else null,
                onFocusChanged = ::onFieldFocusLost
            )

            Spacer(Modifier.height(16.dp))

            RoundedTextField(
                label = "",
                value = expirationDate,
                onValueChange = { expirationDate = it },
                placeholder = "Expiration Date",
                isError = showErrors && expirationDate.isBlank(),
                errorMessage = if (showErrors && expirationDate.isBlank()) "Required Field" else null,
                onFocusChanged = ::onFieldFocusLost
            )

            Spacer(Modifier.height(16.dp))

            RoundedTextField(
                label = "",
                value = cvv,
                onValueChange = { cvv = it },
                placeholder = "CVV",
                isError = showErrors && cvv.isBlank(),
                errorMessage = if (showErrors && cvv.isBlank()) "Required Field" else null,
                onFocusChanged = ::onFieldFocusLost
            )

            Spacer(Modifier.weight(1f))

            ButtonAuthComp(
                text = "Save",
                onClick = {
                    hasSubmitted = true
                    showErrors = true
                    if (isFormValid) {
                        onNavigate("settingsView")
                    }
                },
                enabled = !hasSubmitted || isFormValid
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PaymentMethodConfigViewPreview() {
    PaymentMethodConfigView(onNavigate = {})
}
