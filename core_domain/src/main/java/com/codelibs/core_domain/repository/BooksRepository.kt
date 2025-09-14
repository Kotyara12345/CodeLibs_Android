package com.codelibs.core_domain.repository

import com.codelibs.core_domain.model.book.Book
import com.codelibs.core_domain.model.book.Rubric

interface BooksRepository {
    suspend fun getBooks(
        page: Int = 1,
        rubrics: List<Int>? = null,
        authors: List<Int>? = null,
        langCategory: String? = null,
        yearRelease: Int? = null
    ): List<Book>

    suspend fun getBook(id: Int): Book
    suspend fun getCategories(search: String? = null): List<Rubric>
}