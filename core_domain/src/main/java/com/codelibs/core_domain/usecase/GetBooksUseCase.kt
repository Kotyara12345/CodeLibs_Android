package com.codelibs.core_domain.usecase

import com.codelibs.core_domain.model.book.Book
import com.codelibs.core_domain.repository.BooksRepository

class GetBooksUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        rubrics: List<Int>? = null,
        authors: List<Int>? = null,
        langCategory: String? = null,
        yearRelease: Int? = null
    ): List<Book> {
        return repository.getBooks(
            page = page,
            rubrics = rubrics,
            authors = authors,
            langCategory = langCategory,
            yearRelease = yearRelease
        )
    }
}