package com.hsact.feature_bookpage.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import com.hsact.feature_bookpage.ui.state.CommentsUiState
import com.hsact.feature_bookpage.ui.state.SimilarBooksUiState
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
        if (id != null) {
            loadBookAndSimilarBooksAndComments(id)
        } else {
            _uiState.value = BookPageUiState.Error("Invalid book ID")
        }
    }

    private fun loadBookAndSimilarBooksAndComments(bookId: Int) {
        viewModelScope.launch {
            try {
                val book = booksRepository.getBook(bookId)
                _uiState.value = BookPageUiState.Success(book)
                loadSimilarBooks(bookId)
                loadComments(bookId)
            } catch (e: Exception) {
                _uiState.value = BookPageUiState.Error(e.message ?: "Unknown error")
                Log.e("BookPageViewModel", "Error loading book", e)
            }
        }
    }

    fun loadSimilarBooks(bookId: Int) {
        viewModelScope.launch {
            try {
                val similarBooks = booksRepository.getSimilarBooks(bookId)
                val currentState = _uiState.value
                if (currentState is BookPageUiState.Success) {
                    _uiState.value = currentState.copy(
                        similarBooks = SimilarBooksUiState.Success(similarBooks)
                    )
                }
            } catch (e: Exception) {
                val currentState = _uiState.value
                if (currentState is BookPageUiState.Success) {
                    _uiState.value = currentState.copy(
                        similarBooks = SimilarBooksUiState.Error(
                            e.message ?: "Error loading similar books"
                        )
                    )
                }
            }
        }
    }

    fun loadComments(bookId: Int) {
        viewModelScope.launch {
            try {
                val comments = booksRepository.getComments(bookId)
                val currentState = _uiState.value
                if (currentState is BookPageUiState.Success) {
                    _uiState.value = currentState.copy(
                        comments = CommentsUiState.Success(comments)
                    )
                }
            } catch (e: Exception) {
                val currentState = _uiState.value
                if (currentState is BookPageUiState.Success) {
                    _uiState.value = currentState.copy(
                        comments = CommentsUiState.Error(e.message ?: "Error loading comments")
                    )
                }
                Log.e("BookPageViewModel", "Error loading comments", e)
            }
        }
    }
}