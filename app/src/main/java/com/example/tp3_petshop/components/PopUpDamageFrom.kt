package com.example.tp3_petshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.models.DamageType


@Composable
fun DamagePopup(
    position: Offset,
    selectedPartName: String,
    damageType: DamageType,
    description: String,
    onDamageTypeChange: (DamageType) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier
            .absoluteOffset(x = position.x.dp, y = position.y.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(12.dp)
            .width(250.dp)
    ) {
        Column {
            Text(text = "Parte: $selectedPartName", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Tipo de daño:")
            DropdownMenuBox(damageType, onDamageTypeChange)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Descripción:")
            TextField(
                value = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ej: rayón leve en la puerta") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onSave, modifier = Modifier.weight(1f),
                    enabled = damageType == DamageType.SIN_DANO || description.isNotBlank()
                ) {
                    Text("Guardar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onCancel, modifier = Modifier.weight(1f)) {
                    Text("Cancelar")
                }
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    selected: DamageType,
    onSelect: (DamageType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() })
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DamageType.entries.forEach { type ->
                DropdownMenuItem(onClick = {
                    onSelect(type)
                    expanded = false
                }, text = {
                    Text(type.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() })
                })
            }
        }
    }
}
