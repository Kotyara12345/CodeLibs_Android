package com.codelibs.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddedByDTO(
    val id: Int,
    val username: String
)