package com.codelibs.core_data.repository

import com.codelibs.core_data.remote.api.BooksApiService
import com.codelibs.core_data.remote.mapper.toBookList
import com.codelibs.core_domain.model.book.Book
import com.codelibs.core_domain.repository.BooksRepository
import jakarta.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(
        page: Int,
        rubrics: List<Int>?,
        authors: List<Int>?,
        langCategory: String?,
        yearRelease: Int?
    ): List<Book> {
        return api.getBooks(
            page,
            rubrics,
            authors,
            langCategory,
            yearRelease
        ).toBookList()
    }
}