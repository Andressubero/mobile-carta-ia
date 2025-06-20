package com.example.tp3_petshop.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp3_petshop.components.AccordionItem
import com.example.tp3_petshop.components.TopBarButtonBack

val items = List(5) { index ->
    "Security" to "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus."
}

@Composable
fun FaqView(onNavigate: (value: String) -> Unit) {
    var expandedIndex by remember { mutableIntStateOf(-1) }

    Scaffold(
        topBar = {
            TopBarButtonBack("FAQ") { onNavigate("settingsView") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(
                        top = innerPadding.calculateTopPadding() + 8.dp,
                        start = 24.dp,
                        end = 24.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    )
                )
        ) {
            items.forEachIndexed { index, (title, content) ->
                AccordionItem(
                    title = title,
                    content = content,
                    isExpanded = expandedIndex == index,
                    onExpandChanged = {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

