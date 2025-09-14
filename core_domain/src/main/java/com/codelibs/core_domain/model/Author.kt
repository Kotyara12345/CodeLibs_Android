package com.codelibs.core_domain.model

data class Author(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String?,
    val image: String?
)