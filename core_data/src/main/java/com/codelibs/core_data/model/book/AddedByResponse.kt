package com.codelibs.core_data.model.book

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddedByResponse(
    val id: Int,
    val username: String
)