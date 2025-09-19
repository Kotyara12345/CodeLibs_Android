package com.hsact.feature_catalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelibs.core_domain.repository.BooksRepository
import com.hsact.feature_catalog.ui.state.CatalogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    // Флаг для отображения footer progress в UI (подгрузка следующей страницы)
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private var currentPage = 1
    private var isLoading = false            // защищает от параллельных вызовов
    private var endReached = false           // true, когда сервер вернул пустую страницу
    private var currentRubrics: List<Int> = emptyList()

    /**
     * Загрузить книги
     * @param rubricsId — фильтр по рубрикам (пустой список = без фильтра)
     * @param reset — true = загрузка с первой страницы (сброс), false = загрузить next page (append)
     */
    fun loadBooks(rubricsId: List<Int> = emptyList(), reset: Boolean = true) {
        // Защита от повторных параллельных вызовов
        if (isLoading) return

        if (reset) {
            currentPage = 1
            endReached = false
            currentRubrics = rubricsId
            _uiState.value = CatalogUiState.Loading
        } else {
            // если фильтр поменялся — делаем сброс
            if (rubricsId != currentRubrics) {
                loadBooks(rubricsId, reset = true)
                return
            }
            if (endReached) return // ничего не подгружаем, уже в конце
        }

        viewModelScope.launch {
            isLoading = true
            if (!reset) _isLoadingMore.value = true

            try {
                val rubricsParam = currentRubrics.ifEmpty { null }
                val books = booksRepository.getBooks(page = currentPage, rubrics = rubricsParam)

                if (reset) {
                    // первая страница — заменяем список
                    _uiState.value = CatalogUiState.Success(books)
                    if (books.isEmpty()) endReached = true else currentPage++
                } else {
                    // добавляем к уже имеющемуся списку
                    val prev = (uiState.value as? CatalogUiState.Success)?.books ?: emptyList()
                    if (books.isEmpty()) {
                        endReached = true
                    } else {
                        _uiState.value = CatalogUiState.Success(prev + books)
                        currentPage++
                    }
                }
            } catch (e: Exception) {
                Log.e("CatalogViewModel", "Error loading books", e)
                // Если у нас нет ни одной книги — показываем Error (полный экран).
                // Если уже есть книги — сохраняем текущий список и можно показать Snack bar через event-flow (ниже идея).
                val hadData =
                    (uiState.value as? CatalogUiState.Success)?.books?.isNotEmpty() == true
                if (!hadData) {
                    _uiState.value = CatalogUiState.Error(e.message ?: "Unknown error")
                } else {
                    // ничего не меняем в uiState — можно передать одноразовое событие об ошибке
                    // (если нужно, добавим MutableSharedFlow<String> для сообщений)
                }
            } finally {
                isLoading = false
                _isLoadingMore.value = false
            }
        }
    }

    // Удобный alias для вызова из UI при скролле
    fun loadNextPage() = loadBooks(currentRubrics, reset = false)

    // Перезагрузить текущий набор (pull-to-refresh, смена фильтра и т.п.)
    fun refresh() = loadBooks(currentRubrics, reset = true)

    // Для UI: есть ли ещё страницы
    fun hasMore(): Boolean = !endReached
}