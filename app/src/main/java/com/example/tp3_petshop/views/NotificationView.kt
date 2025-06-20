package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.SwitchSection
import com.example.tp3_petshop.components.TopBarButtonBack
import com.example.tp3_petshop.models.SwitchItemData
import com.example.tp3_petshop.models.SwitchSectionData

@Composable
fun NotificationView (
    onNavigate: (value: String) -> Unit,
) {
    fun onToggle(
        sectionTitle: String,
        itemLabel: String,
        isEnabled: Boolean,
        sectionsState: SnapshotStateList<SwitchSectionData>
    ) {
        val sectionIndex = sectionsState.indexOfFirst { it.title == sectionTitle }
        if (sectionIndex == -1) return

        val itemIndex = sectionsState[sectionIndex].items.indexOfFirst { it.label == itemLabel }
        if (itemIndex == -1) return

        val updatedItem = sectionsState[sectionIndex].items[itemIndex].copy(isEnabled = isEnabled)

        val updatedSection = sectionsState[sectionIndex].copy(
            items = sectionsState[sectionIndex].items.toMutableList().apply {
                set(itemIndex, updatedItem)
            }
        )

        sectionsState[sectionIndex] = updatedSection
    }


    val sectionsState = remember {
        mutableStateListOf(
            SwitchSectionData("Social", listOf(
                SwitchItemData("Liked Post", true),
                SwitchItemData("New Message", true)
            )),
            SwitchSectionData("Store", listOf(
                SwitchItemData("Item Sold", true)
            ))
        )
    }

    Scaffold(
        topBar = {
            TopBarButtonBack("Notification") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            sectionsState.forEach { section ->
                SwitchSection(
                    section = section,
                    onToggleItem = { sectionTitle, itemLabel, enabled ->
                        onToggle(sectionTitle, itemLabel, enabled, sectionsState)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}