package com.hsact.feature_bookpage.ui

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsact.feature_bookpage.ui.section.BookPageContent
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import com.hsact.feature_bookpage.viewmodel.BookPageViewModel

@Composable
fun BookPageScreen(
    viewModel: BookPageViewModel = hiltViewModel(),
    onRubricClick: (Int, String) -> Unit,
    onSimilarBookClick: (Int) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(c: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id != -1L) {
                    viewModel.onDownloadCompleted(id)
                }
            }
        }
        val flags =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                Context.RECEIVER_NOT_EXPORTED
            } else {
                0
            }
        context.registerReceiver(
            receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            flags
        )
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    when (state) {
        is BookPageUiState.Loading -> CircularProgressIndicator()
        is BookPageUiState.Error -> Text("Error: ${state.message}")
        is BookPageUiState.Success -> {
            BookPageContent(
                state = state,
                onRubricClick = { rubricId, rubricName ->
                    onRubricClick(rubricId, rubricName)
                },
                onDownloadClick = { viewModel.onDownloadClick() },
                onBuyClick = { viewModel.onBuyClick() },
                onSimilarBookClick = { otherBookId ->
                    onSimilarBookClick(otherBookId)
                }
            )
        }
    }
}