package com.codelibs.feature_profile.ui.state

import com.codelibs.core_domain.model.User

sealed class ProfileUiState {
    object Loading : ProfileUiState()

    // Пользователь не авторизован
    object Unauthorized : ProfileUiState()

    // Успешная авторизация — в модели храним данные юзера
    data class Authorized(val user: User) : ProfileUiState()

    // Ошибка (например, неверный пароль, сетевые проблемы и т.п.)
    data class Error(val message: String) : ProfileUiState()
}