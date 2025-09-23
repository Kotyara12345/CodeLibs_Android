package com.hsact.feature_bookpage.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.codelibs.core_domain.repository.FileDownloader
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
    private val fileDownloader: FileDownloader,
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

    private var currentDownloadId: Long? = null
    fun onDownloadClick() {
        val currentState = _uiState.value
        if (currentState is BookPageUiState.Success) {
            val url = currentState.book.bookFile
            val title = currentState.book.title

            if (!url.isNullOrEmpty()) {
                val extension = url.substringAfterLast('.', "pdf")
                val fileName = "$title.$extension"

                currentDownloadId =
                    fileDownloader.download(url, fileName) { progress, isDownloading ->
                        val currentState = _uiState.value
                        if (currentState is BookPageUiState.Success) {
                            _uiState.value = currentState.copy(
                                downloadProgress = progress,
                                isDownloading = isDownloading
                            )
                        }
                    }
            }
        }
    }

    fun onDownloadCompleted(id: Long) {
        // проверим, что это наш последний запрос
        if (id == currentDownloadId) {
            val currentState = _uiState.value
            if (currentState is BookPageUiState.Success) {
                _uiState.value = currentState.copy(isDownloading = false)
            }
        }
    }

    fun onBuyClick() {
        val currentState = _uiState.value
        if (currentState is BookPageUiState.Success) {
            val url = currentState.book.url // ссылка на покупку
            if (!url.isNullOrEmpty()) {
                // TODO: открыть ссылку через Intent
            } else {
                Log.e("BookPageViewModel", "No buy URL available")
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
}