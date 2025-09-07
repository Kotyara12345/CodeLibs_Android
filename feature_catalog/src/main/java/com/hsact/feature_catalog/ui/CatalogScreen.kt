package com.hsact.feature_catalog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsact.feature_catalog.ui.state.CatalogUiState
import com.hsact.feature_catalog.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    onItemClick: (String) -> Unit,
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
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.books) { book ->
                    book.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { onItemClick(book.id.toString()) }
                        )
                    }
                }
            }
        }
    }
}