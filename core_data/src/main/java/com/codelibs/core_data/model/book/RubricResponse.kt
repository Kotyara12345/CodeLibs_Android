package com.codelibs.core_data.model.book

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RubricResponse(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String?
)