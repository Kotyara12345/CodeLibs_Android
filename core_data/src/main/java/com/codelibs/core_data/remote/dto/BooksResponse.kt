package com.codelibs.core_data.remote.dto

import com.codelibs.core_data.model.book.BookResponse

data class BooksResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookResponse>
)