package com.codelibs.feature_rubrics.ui.state

import com.codelibs.core_domain.model.book.Rubric

sealed class RubricsUiState {
    object Loading : RubricsUiState()
    data class Success(val rubrics: List<Rubric>) : RubricsUiState()
    data class Error(val message: String) : RubricsUiState()
}