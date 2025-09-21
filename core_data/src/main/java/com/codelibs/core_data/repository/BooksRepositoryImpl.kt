package com.codelibs.core_data.repository

import com.codelibs.core_network.api.BooksApiService
import com.codelibs.core_data.mapper.toBookList
import com.codelibs.core_data.mapper.toDomain
import com.codelibs.core_domain.model.Book
import com.codelibs.core_domain.model.Comment
import com.codelibs.core_domain.model.Rubric
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

    override suspend fun getBook(id: Int): Book {
        return api.getBook(id).toDomain()
    }
    override suspend fun getRubrics(search: String?): List<Rubric> {
        return api.getRubrics(search).map { it.toDomain() }
    }
    override suspend fun getComments(bookId: Int): List<Comment> {
        return api.getComments(bookId).map { it.toDomain() }
    }
}