package com.codelibs.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RubricDTO(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String?
)