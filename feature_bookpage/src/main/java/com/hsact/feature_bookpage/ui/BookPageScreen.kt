package com.hsact.feature_bookpage.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codelibs.core_ui.components.screenPadding
import com.codelibs.core_ui.utils.fromHtmlToAnnotatedString
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import com.hsact.feature_bookpage.viewmodel.BookPageViewModel

@Composable
fun BookPageScreen(
    bookId: Int,
    viewModel: BookPageViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    when (state) {
        is BookPageUiState.Loading -> CircularProgressIndicator()
        is BookPageUiState.Error -> Text("Error: ${state.message}")
        is BookPageUiState.Success -> {
            Column(Modifier
                .fillMaxSize()
                .screenPadding()
                .verticalScroll(rememberScrollState())) {
                Text(state.book.authors.first().name)
                Spacer(Modifier.height(16.dp))
                Text(state.book.title.toString())
                Spacer(Modifier.height(16.dp))
                Text(state.book.content?.fromHtmlToAnnotatedString() ?: AnnotatedString(""))
            }
        }
    }
}