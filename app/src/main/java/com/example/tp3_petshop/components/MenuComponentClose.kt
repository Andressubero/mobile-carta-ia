package com.example.tp3_petshop.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.models.MenuItem

@Composable
fun ItemRowClose(item: MenuItem, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Text(item.label, modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Cerrar"
        )
    }
}


@Composable
fun MenuComponentClose(title: String, items: List<MenuItem>, onClick: (String) -> Unit) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    items.forEach { item ->
        ItemRowClose(item = item) { onClick(item.value) }
    }
}
