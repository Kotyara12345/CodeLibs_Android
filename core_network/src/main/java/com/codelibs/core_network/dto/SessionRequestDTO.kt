package com.codelibs.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionRequestDTO(
    val username: String,
    val password: String
)