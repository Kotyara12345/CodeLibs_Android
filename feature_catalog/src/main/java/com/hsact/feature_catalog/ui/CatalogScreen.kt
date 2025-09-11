package com.hsact.feature_catalog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelibs.core_ui.components.screenPadding
import com.hsact.feature_catalog.ui.components.BookItem
import com.hsact.feature_catalog.ui.state.CatalogUiState
import com.hsact.feature_catalog.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    onItemClick: (Int) -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is CatalogUiState.Loading -> {
            CircularProgressIndicator()
        }
        is CatalogUiState.Error -> {
            Text("Error: ${state.message}")
        }
        is CatalogUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().screenPadding()
            ) {
//                items(state.books) { book ->
//                    book.title?.let {
//                        Text(
//                            text = it,
//                            modifier = Modifier
//                                .fillMaxSize().padding(bottom = 16.dp)
//                                .clickable { onItemClick(book.id) }
//                        )
//                    }
//                }
                items(state.books) { book ->
                    BookItem(book = book, onItemClick = onItemClick)
                }
            }
        }
    }
}