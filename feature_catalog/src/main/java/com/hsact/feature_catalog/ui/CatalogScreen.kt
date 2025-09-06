package com.hsact.feature_catalog.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun CatalogScreen(
    onItemClick: (String) -> Unit
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(text = "Каталог")
        }
        item {
            Button(onClick = { onItemClick("book1") }) {
                Text("Книга 1")
            }
        }
        item {
            Button(onClick = { onItemClick("book2") }) {
                Text("Книга 2")
            }
        }
    }
}