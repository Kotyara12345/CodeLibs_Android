package com.hsact.feature_bookpage.ui.state

import com.codelibs.core_domain.model.book.Book

sealed class BookPageUiState{
    object Loading : BookPageUiState()
    data class Success(val book: Book) : BookPageUiState()
    data class Error(val message: String) : BookPageUiState()
}