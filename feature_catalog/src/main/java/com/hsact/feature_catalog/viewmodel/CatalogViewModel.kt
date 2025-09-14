package com.hsact.feature_catalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.hsact.feature_catalog.ui.state.CatalogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val uiState: StateFlow<CatalogUiState> = _uiState

    fun loadBooks(rubricId: Int? = null) {
        viewModelScope.launch {
            try {
                val books = if (rubricId != null) {
                    booksRepository.getBooks(page = 1, rubrics = listOf(rubricId))
                } else {
                    booksRepository.getBooks(page = 1)
                }
                _uiState.value = CatalogUiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = CatalogUiState.Error(e.message ?: "Unknown error")
                Log.e("CatalogViewModel", "Error loading books", e)
            }
        }
    }
}