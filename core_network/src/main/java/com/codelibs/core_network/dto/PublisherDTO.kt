package com.codelibs.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublisherDTO(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String,
    val image: String?
)