package com.example.tp3_petshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp3_petshop.models.SwitchItemData
import com.example.tp3_petshop.models.SwitchSectionData

@Composable
fun SwitchItem(
    item: SwitchItemData,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.label, fontSize = 14.sp)
        Switch(
            checked = item.isEnabled,
            onCheckedChange = { onToggle(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF8B5CF6),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun SwitchSection(
    section: SwitchSectionData,
    onToggleItem: (sectionTitle: String, itemLabel: String, isEnabled: Boolean) -> Unit
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = section.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 29.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        section.items.forEach { item ->
            SwitchItem(
                item = item,
                onToggle = { newValue ->
                    onToggleItem(section.title, item.label, newValue)
                }
            )
        }
    }
}
