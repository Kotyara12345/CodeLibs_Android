package com.codelibs.feature_profile.ui.state

import com.codelibs.core_domain.model.User

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val rubrics: User) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}