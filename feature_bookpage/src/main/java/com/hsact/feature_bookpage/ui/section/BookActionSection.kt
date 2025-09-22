package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BookActionSection(modifier: Modifier) {
    Column(modifier = modifier) {
        //Кнопка "Купить"
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Купить")
        }
        Spacer(Modifier.height(2.dp))
        Text(
            text = "Поддержите автора, купив эту книгу",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(Modifier.height(16.dp))

        //Кнопка "Скачать"
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Скачать")
        }
    }
}