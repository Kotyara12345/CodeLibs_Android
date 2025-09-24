package com.hsact.feature_bookpage.ui.section

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hsact.feature_bookpage.ui.components.DownloadProgressBar

@Composable
internal fun BookActionSection(
    modifier: Modifier,
    isDownloading: Boolean,
    downloadProgress: Int,
    onBuyClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
    val context = LocalContext.current
    // Плавная анимация прогресса
    val animatedProgress by animateFloatAsState(
        targetValue = (downloadProgress.coerceIn(0, 100) / 100f),
        label = "downloadProgress"
    )
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
            DownloadProgressBar(downloadProgress, animatedProgress)
        }
        // Показываем Toast, когда прогресс достиг 100%
        if (downloadProgress >= 100) {
            LaunchedEffect(Unit) {
                Toast.makeText(
                    context,
                    "Файл сохранён в загрузки",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}