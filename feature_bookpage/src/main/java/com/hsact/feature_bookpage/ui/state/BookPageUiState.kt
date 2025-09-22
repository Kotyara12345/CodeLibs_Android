package com.hsact.feature_bookpage.ui.state

import com.codelibs.core_domain.model.Book

sealed class BookPageUiState {
    object Loading : BookPageUiState()
    data class Success(
        val book: Book,
        val similarBooks: SimilarBooksUiState = SimilarBooksUiState.Loading,
        val comments: CommentsUiState = CommentsUiState.Loading
    ) : BookPageUiState()
    data class Error(val message: String) : BookPageUiState()
}