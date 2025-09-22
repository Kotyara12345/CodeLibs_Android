package com.hsact.feature_bookpage.ui.state

import com.codelibs.core_domain.model.Comment

sealed class CommentsUiState {
    object Loading : CommentsUiState()
    data class Success(val comments: List<Comment>) : CommentsUiState()
    data class Error(val message: String) : CommentsUiState()
}