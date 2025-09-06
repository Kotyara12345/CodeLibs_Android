package com.codelibs.core_data.model.book

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublisherResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String?,
    val image: String?
)