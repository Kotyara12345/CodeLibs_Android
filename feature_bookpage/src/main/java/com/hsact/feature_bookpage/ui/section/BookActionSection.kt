package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun BookActionSection(
    modifier: Modifier,
    isDownloading: Boolean,
    downloadProgress: Int,
    onBuyClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
    Column(modifier = modifier) {
        //Кнопка "Купить"
        Button(
            onClick = onBuyClick,
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
            onClick = onDownloadClick,
            enabled = !isDownloading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isDownloading) "Скачивается..." else "Скачать")
        }

        if (isDownloading) {
            Spacer(Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$downloadProgress%",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = {
                        downloadProgress.coerceIn(0, 100) / 100f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        }
    }
}