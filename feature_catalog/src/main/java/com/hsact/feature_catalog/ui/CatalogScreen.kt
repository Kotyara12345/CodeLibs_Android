package com.hsact.feature_catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codelibs.core_ui.components.screenPadding
import com.hsact.feature_catalog.ui.components.BookItem
import com.hsact.feature_catalog.ui.state.CatalogUiState
import com.hsact.feature_catalog.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    rubricId: Int? = null,
    rubricName: String? = null,
    onItemClick: (Int) -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    // Загружаем книги при изменении рубрики (или при старте)
    LaunchedEffect(rubricId) {
        viewModel.loadBooks(rubricId)
    }

    Column(modifier = Modifier.fillMaxSize().screenPadding()) {
        // Показываем заголовок категории, если он есть
        rubricName?.takeIf { it.isNotBlank() }?.let { name ->
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }

        when (state) {
            is CatalogUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is CatalogUiState.Error -> {
                Text("Error: ${state.message}")
            }
            is CatalogUiState.Success -> {
                LazyColumn {
                    items(state.books) { book ->
                        BookItem(book = book, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}