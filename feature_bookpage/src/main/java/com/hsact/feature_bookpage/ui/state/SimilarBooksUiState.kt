package com.hsact.feature_bookpage.ui.state

import com.codelibs.core_domain.model.Book

sealed class SimilarBooksUiState {
    object Loading : SimilarBooksUiState()
    data class Success(val books: List<Book>) : SimilarBooksUiState()
    data class Error(val message: String) : SimilarBooksUiState()
}