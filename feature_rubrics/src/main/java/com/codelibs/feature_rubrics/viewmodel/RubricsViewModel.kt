package com.codelibs.feature_rubrics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.codelibs.feature_rubrics.ui.state.RubricsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RubricsViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RubricsUiState>(RubricsUiState.Loading)
    val uiState: StateFlow<RubricsUiState> = _uiState

    init {
        loadRubrics()
    }

    private fun loadRubrics() {
        viewModelScope.launch {
            try {
                val rubrics = booksRepository.getRubrics()
                _uiState.value = RubricsUiState.Success(rubrics)
            } catch (e: Exception) {
                _uiState.value = RubricsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}