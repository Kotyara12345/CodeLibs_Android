package com.codelibs.feature_rubrics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.codelibs.feature_rubrics.ui.state.RubricsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RubricsViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RubricsUiState>(RubricsUiState.Loading)
    val uiState: StateFlow<RubricsUiState> = _uiState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    init {
        observeQuery()
        loadRubrics() // при старте грузим все рубрики
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    @OptIn(FlowPreview::class)
    private fun observeQuery() {
        viewModelScope.launch {
            _query
                .debounce(300) // ждать остановку ввода
                .distinctUntilChanged()
                .collect { q ->
                    if (q.isBlank()) {
                        loadRubrics()
                    } else {
                        searchRubrics(q)
                    }
                }
        }
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

    private fun searchRubrics(query: String) {
        viewModelScope.launch {
            try {
                val rubrics = booksRepository.getRubrics(query)
                _uiState.value = RubricsUiState.Success(rubrics)
            } catch (e: Exception) {
                _uiState.value = RubricsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}