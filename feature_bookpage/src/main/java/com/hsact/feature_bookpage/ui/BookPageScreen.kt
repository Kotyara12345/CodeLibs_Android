package com.hsact.feature_bookpage.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsact.feature_bookpage.ui.section.BookPageContent
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import com.hsact.feature_bookpage.viewmodel.BookPageViewModel

@Composable
fun BookPageScreen(
    viewModel: BookPageViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    when (state) {
        is BookPageUiState.Loading -> CircularProgressIndicator()
        is BookPageUiState.Error -> Text("Error: ${state.message}")
        is BookPageUiState.Success -> {
            BookPageContent(state)
        }
    }
}