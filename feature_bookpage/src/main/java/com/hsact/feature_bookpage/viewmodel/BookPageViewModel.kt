package com.hsact.feature_bookpage.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<BookPageUiState>(BookPageUiState.Loading)
    val uiState: StateFlow<BookPageUiState> = _uiState

    init {
        val id = savedStateHandle.get<Int>("bookId")
        viewModelScope.launch {
            if (id != null) {
                try {
                    val book = booksRepository.getBook(id)
                    _uiState.value = BookPageUiState.Success(book)
                } catch (e: Exception) {
                    _uiState.value = BookPageUiState.Error(e.message ?: "Unknown error")
                    Log.e("CatalogViewModel", "Error loading books", e)
                }
            }
            else {
                _uiState.value = BookPageUiState.Error("Invalid book ID")
            }
        }
    }
}