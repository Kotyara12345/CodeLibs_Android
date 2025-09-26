package com.codelibs.core_domain.repository

import com.codelibs.core_domain.model.Book
import com.codelibs.core_domain.model.Comment
import com.codelibs.core_domain.model.Rubric

interface BooksRepository {
    suspend fun getBooks(
        page: Int = 1,
        rubrics: List<Int>? = null,
        authors: List<Int>? = null,
        langCategory: String? = null,
        yearRelease: Int? = null
    ): List<Book>

    suspend fun getBook(id: Int): Book
    suspend fun getRubrics(search: String? = null): List<Rubric>
    suspend fun getSimilarBooks(bookId: Int): List<Book>
    suspend fun getFavoriteBooks(search: String? = null, page: Int = 1): List<Book>
    suspend fun getComments(bookId: Int): List<Comment>
}