package com.hsact.feature_bookpage.ui.state

import com.codelibs.core_domain.model.Book
import com.codelibs.core_domain.model.Comment

sealed class BookPageUiState {
    object Loading : BookPageUiState()
    data class Success(
        val book: Book,
        val comments: CommentsUiState = CommentsUiState.Loading
    ) : BookPageUiState()
    data class Error(val message: String) : BookPageUiState()
}

sealed class CommentsUiState {
    object Loading : CommentsUiState()
    data class Success(val comments: List<Comment>) : CommentsUiState()
    data class Error(val message: String) : CommentsUiState()
}