package com.codelibs.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookDTO>
)