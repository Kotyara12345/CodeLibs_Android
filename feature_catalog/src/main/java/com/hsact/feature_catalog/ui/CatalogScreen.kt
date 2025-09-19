package com.hsact.feature_catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelibs.core_ui.components.screenPadding
import com.hsact.feature_catalog.ui.components.BookItem
import com.hsact.feature_catalog.ui.state.CatalogUiState
import com.hsact.feature_catalog.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    rubricsId: List<Int> = emptyList(),
    rubricName: String? = null,
    onItemClick: (Int) -> Unit,
    viewModel: CatalogViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()

    // Список для контроля скролла
    val listState = rememberLazyListState()

    // Загружаем книги при изменении рубрики (или при старте)
    LaunchedEffect(rubricsId) {
        viewModel.loadBooks(rubricsId, reset = true)
    }

    // Автоподгрузка при достижении конца списка
    LaunchedEffect(listState, isLoadingMore) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val total = listState.layoutInfo.totalItemsCount
                if (
                    lastVisible != null &&
                    total >= 10 && // <-- минимальный размер списка для автоподгрузки
                    lastVisible >= total - 3 &&
                    !isLoadingMore &&
                    viewModel.hasMore()
                ) {
                    viewModel.loadNextPage()
                }
            }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .screenPadding()) {
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

        when (val s = state) {
            is CatalogUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is CatalogUiState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${s.message}")
                }
            }
            is CatalogUiState.Success -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(s.books) { book ->
                        BookItem(book = book, onItemClick = onItemClick)
                    }

                    if (isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}