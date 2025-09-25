package com.codelibs.feature_profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.model.User
import com.codelibs.core_domain.repository.AuthRepository
import com.codelibs.feature_profile.ui.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState
    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                if (user != null) {
                    val detailedUser = authRepository.getAccount(user.id)
                    _uiState.value = ProfileUiState.Authorized(detailedUser)
                } else {
                    _uiState.value = ProfileUiState.Unauthorized
                }
            }
        }

    }
    fun login(username: String, password: String) {
        _uiState.value = ProfileUiState.Loading
        viewModelScope.launch {
            try {
                val user = authRepository.login(username, password)
                val detailedUser = authRepository.getAccount(user.id)
                val mockUser = User(
                    id = user.id,
                    firstName = "John",
                    lastName = "Doe",
                    username = user.username,
                    sendMessages = false,
                    avatarUrl = null,
                    email = "john.doe@example.com"
                )
                    _uiState.value = ProfileUiState.Authorized(detailedUser)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
//            val userId = userPrefs.getUserId() ?: return@launch
            val user = authRepository.getAccount(userId)
            if (user != null) {
                _uiState.value = ProfileUiState.Authorized(user)
            } else {
                _uiState.value = ProfileUiState.Error("Не удалось загрузить данные")
            }
        }
    }

    fun logout() {
        _uiState.value = ProfileUiState.Unauthorized
    }
}