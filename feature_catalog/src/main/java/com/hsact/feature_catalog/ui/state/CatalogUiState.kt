package com.hsact.feature_catalog.ui.state

import com.codelibs.core_domain.model.book.Book

sealed class CatalogUiState {
    object Loading : CatalogUiState()
    data class Success(val books: List<Book>) : CatalogUiState()
    data class Error(val message: String) : CatalogUiState()
}